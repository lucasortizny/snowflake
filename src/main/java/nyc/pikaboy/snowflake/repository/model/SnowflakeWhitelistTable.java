package nyc.pikaboy.snowflake.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "whitelist")
public class SnowflakeWhitelistTable {
    @Column(name = "whitelisted_id")
    @Id
    public String whitelistId;
    @Column(name = "timestamp")
    public Instant timestamp;
}

