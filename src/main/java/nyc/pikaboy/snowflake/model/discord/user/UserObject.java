package nyc.pikaboy.snowflake.model.discord.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserObject {
    public String id;
    public String username;
    public String discriminator;
    public String avatar;
    public boolean verified;
    public String email;
    public int flags;
    public String banner;
    public int accent_color;
    public int premium_type;
    public int public_flags;
}
