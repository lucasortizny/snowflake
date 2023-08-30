package nyc.pikaboy.snowflake.repository;

import nyc.pikaboy.snowflake.repository.model.SnowflakeAuthenticationTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<SnowflakeAuthenticationTable, String> {
}
