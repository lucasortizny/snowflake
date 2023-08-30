package nyc.pikaboy.snowflake.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "discord_auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnowflakeAuthenticationTable {
    @Column(name = "discord_id")
    public String discordId;
    @Column(name = "timestamp")
    public Instant timestamp;
    @Column(name = "authorization_uuid")
    @Id
    public String authorizationUuid;
    @Column(name = "consumption_indicator")
    public boolean consumptionIndicator;
}
