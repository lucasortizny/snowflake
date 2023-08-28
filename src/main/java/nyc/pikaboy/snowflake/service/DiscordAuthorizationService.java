package nyc.pikaboy.snowflake.service;

import org.springframework.stereotype.Service;

@Service
public interface DiscordAuthorizationService {

    String exchangeToken(String code);

}
