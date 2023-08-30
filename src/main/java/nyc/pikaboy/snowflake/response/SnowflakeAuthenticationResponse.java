package nyc.pikaboy.snowflake.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SnowflakeAuthenticationResponse {

    public SnowflakeStatus status;
    public String description;
    public String accessCode;

    private static final String SNOWFLAKE_INSTRUCTION = "You have been approved for use of this service. Please insert" +
            " this code to the Snowflake CLI.";
    private static final String DENIAL_DISCORD = "You have been denied access to this service because you are not in the" +
            " correct Discord server.";
    private static final String DENIAL_BLACKLIST = "You have been denied access to this service due to a whitelist " +
            "or blacklist.";
    private static final String DENIAL_ERROR = "This service is currently not functioning as intended. Please try again " +
            "later or contact Administrator.";
    public static SnowflakeAuthenticationResponse generateDiscordFail(){
        return new SnowflakeAuthenticationResponse(SnowflakeStatus.DENIED, DENIAL_DISCORD, null);
    }
    public static SnowflakeAuthenticationResponse generateBlacklistFail(){
        return new SnowflakeAuthenticationResponse(SnowflakeStatus.DENIED, DENIAL_BLACKLIST, null);
    }
    public static SnowflakeAuthenticationResponse generateSuccess(String uuid){
        return new SnowflakeAuthenticationResponse(SnowflakeStatus.APPROVED, SNOWFLAKE_INSTRUCTION, uuid);
    }
    public static SnowflakeAuthenticationResponse generateServiceFail(){
        return new SnowflakeAuthenticationResponse(SnowflakeStatus.UNAVAILABLE, DENIAL_ERROR, null);
    }

}
