package nyc.pikaboy.snowflake.repository;

import nyc.pikaboy.snowflake.repository.model.SnowflakeWhitelistTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhitelistRepository extends CrudRepository<SnowflakeWhitelistTable, String> {
}
