package nyc.pikaboy.snowflake.service;

import lombok.extern.slf4j.Slf4j;
import nyc.pikaboy.snowflake.model.discord.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class DiscordAuthorizationServiceImpl {
    @Value("${discord.properties.clientId}")
    String clientId;
    @Value("${discord.properties.clientSecret}")
    String clientSecret;
    @Value("${discord.properties.redirectUri}")
    String redirectUri;
    @Value("${discord.properties.baseUri}")
    String baseUri;
    RestTemplate restTemplate;

    public DiscordAuthorizationServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
//    @Override
    public Optional<TokenResponse> exchangeToken(String code) {
        try {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap();
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
}
