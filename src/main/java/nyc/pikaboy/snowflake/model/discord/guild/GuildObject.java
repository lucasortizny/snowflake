package nyc.pikaboy.snowflake.model.discord.guild;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GuildObject {
    public String id;
    public String name;
    public String icon;
    public boolean owner;
    public String permissions;
    public List<String> features;
}
