package nyc.pikaboy.snowflake.service.discord;

import nyc.pikaboy.snowflake.model.discord.TokenResponse;
import nyc.pikaboy.snowflake.model.discord.guild.GuildObject;
import nyc.pikaboy.snowflake.model.discord.user.UserObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiscordService {

    Optional<TokenResponse> exchangeToken(String code);
    List<GuildObject> retrieveGuilds(TokenResponse response);
    List<GuildObject> retrieveGuilds(String accessToken);

    UserObject retrieveUserInfo(TokenResponse response);
    UserObject retrieveUserInfo(String accessToken);

}
