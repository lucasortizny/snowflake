package nyc.pikaboy.snowflake.service.discord;

import lombok.extern.slf4j.Slf4j;
import nyc.pikaboy.snowflake.model.discord.TokenResponse;
import nyc.pikaboy.snowflake.model.discord.guild.GuildObject;
import nyc.pikaboy.snowflake.model.discord.user.UserObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class DiscordServiceImpl implements DiscordService {
    @Value("${discord.properties.clientId}")
    String clientId;
    @Value("${discord.properties.clientSecret}")
    String clientSecret;
    @Value("${discord.properties.redirectUri}")
    String redirectUri;
    @Value("${discord.properties.baseUri}")
    String baseUri;
    RestTemplate restTemplate;

    public DiscordServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Optional<TokenResponse> exchangeToken(String code) {
        try {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("client_id", clientId);
            formData.add("client_secret", clientSecret);
            formData.add("grant_type", "authorization_code");
            formData.add("code", code);
            formData.add("redirect_uri", redirectUri);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            HttpEntity<Map<?,?>> entity = new HttpEntity<>(formData, headers);
            TokenResponse response = restTemplate.exchange(baseUri + "oauth2/token",
                    HttpMethod.POST,
                    entity,
                    TokenResponse.class
            ).getBody();
            return Optional.ofNullable(response);

        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GuildObject> retrieveGuilds(TokenResponse response) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(response.getAccess_token());
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            ParameterizedTypeReference<List<GuildObject>> typeRef = new ParameterizedTypeReference<>() {
            };
            List<GuildObject> obtainedGuilds = (List<GuildObject>) restTemplate.exchange(
                    baseUri + "users/@me/guilds",
                    HttpMethod.GET,
                    entity,
                    typeRef
            ).getBody();
            if (obtainedGuilds == null) {
                return null;
            }
            return obtainedGuilds;
        } catch (Exception e) {
            log.error("Error encountered grabbing. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<GuildObject> retrieveGuilds(String accessToken) {
        TokenResponse responseTemplate = new TokenResponse();
        responseTemplate.setAccess_token(accessToken);
        return retrieveGuilds(responseTemplate);
    }

    @Override
    public UserObject retrieveUserInfo(TokenResponse response) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(response.getAccess_token());
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            UserObject obtainedUser = restTemplate.exchange(
                    baseUri + "users/@me",
                    HttpMethod.GET,
                    entity,
                    UserObject.class
            ).getBody();
            return obtainedUser;
        } catch (Exception e) {
            log.error("Error encountered grabbing. " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserObject retrieveUserInfo(String accessToken) {
        return null;
    }
}
