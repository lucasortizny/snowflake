package nyc.pikaboy.snowflake.model.discord;

import lombok.Data;

@Data
public class TokenResponse {
    public String token_type;
    public String access_token;
    public int expires_in;
    public String refresh_token;
    public String scope;
}
