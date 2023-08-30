package nyc.pikaboy.snowflake.service.discord;

import nyc.pikaboy.snowflake.model.discord.TokenResponse;
import nyc.pikaboy.snowflake.model.discord.guild.GuildObject;
import nyc.pikaboy.snowflake.model.discord.user.UserObject;
import nyc.pikaboy.snowflake.response.SnowflakeAuthenticationResponse;

import java.util.List;

public interface DiscordAuthSnowflakeGenService {
    SnowflakeAuthenticationResponse authenticate(String code);

    boolean searchForGuild(List<GuildObject> guilds, String guildId);

    boolean searchForIdWhitelist(TokenResponse tokenResponse);

    SnowflakeAuthenticationResponse generateAuthFlow(TokenResponse response);
}
