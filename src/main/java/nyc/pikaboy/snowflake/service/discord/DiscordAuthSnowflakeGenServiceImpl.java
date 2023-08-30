package nyc.pikaboy.snowflake.service.discord;

import lombok.extern.slf4j.Slf4j;
import nyc.pikaboy.snowflake.model.discord.TokenResponse;
import nyc.pikaboy.snowflake.model.discord.guild.GuildObject;
import nyc.pikaboy.snowflake.model.discord.user.UserObject;
import nyc.pikaboy.snowflake.repository.AuthenticationRepository;
import nyc.pikaboy.snowflake.repository.WhitelistRepository;
import nyc.pikaboy.snowflake.repository.model.SnowflakeAuthenticationTable;
import nyc.pikaboy.snowflake.response.SnowflakeAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DiscordAuthSnowflakeGenServiceImpl implements DiscordAuthSnowflakeGenService {
    @Autowired
    DiscordService discordService;
    @Autowired
    AuthenticationRepository authenticationRepository;
    @Autowired
    WhitelistRepository whitelistRepository;
    @Value("${snowflake.server-id}")
    String snowflakeDiscordServerId;
    @Override
    public SnowflakeAuthenticationResponse authenticate(String code) {
        Optional<TokenResponse> response = discordService.exchangeToken(code);
        if (response.isPresent()) {
            boolean withinGuild = searchForGuild(discordService.retrieveGuilds(response.get()), snowflakeDiscordServerId);
            boolean withinWhitelist = searchForIdWhitelist(response.get());
            if (withinWhitelist && withinGuild){
                return generateAuthFlow(response.get());
            }
            if (!withinGuild){
                return SnowflakeAuthenticationResponse.generateDiscordFail();
            }
            if (!withinWhitelist){
                return SnowflakeAuthenticationResponse.generateBlacklistFail();
            }


        }
        return SnowflakeAuthenticationResponse.generateDiscordFail();

    }

    @Override
    public boolean searchForGuild(List<GuildObject> guilds, String guildId) {
        return guilds.stream().anyMatch((guild) -> guild.getId().equals(guildId));
    }

    @Override
    public boolean searchForIdWhitelist(TokenResponse tokenResponse) {
        UserObject userObject = discordService.retrieveUserInfo(tokenResponse);
        return whitelistRepository.existsById(userObject.getId());
    }

    @Override
    public SnowflakeAuthenticationResponse generateAuthFlow(TokenResponse response) {
        UserObject userObject = discordService.retrieveUserInfo(response);
        log.debug("User Object is {}", userObject.toString());
        String uuid = UUID.randomUUID().toString();

        try {
            SnowflakeAuthenticationTable entry = new SnowflakeAuthenticationTable(userObject.getId(), Instant.now(), uuid,
                    false);
            log.debug("Attempting save of object {}", entry);
            authenticationRepository.save(entry);
            return SnowflakeAuthenticationResponse.generateSuccess(uuid);
        } catch (IllegalArgumentException e) {
            log.info("User Object ended up null. (Is discord service down?) ", e);
            return SnowflakeAuthenticationResponse.generateServiceFail();
        } catch (OptimisticLockingFailureException e){
            log.info("Persistence entries and actual DB entries differ.", e);
        } catch (NullPointerException e) {
            log.info("Null was returned by the user service. (Is discord service down?) ", e);
        }
        return SnowflakeAuthenticationResponse.generateServiceFail();

    }


}
