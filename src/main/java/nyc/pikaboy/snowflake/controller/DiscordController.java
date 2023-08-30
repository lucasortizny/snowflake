package nyc.pikaboy.snowflake.controller;

import nyc.pikaboy.snowflake.service.discord.DiscordAuthSnowflakeGenService;
import nyc.pikaboy.snowflake.service.discord.DiscordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class DiscordController implements DiscordEndpoint{
    public RestTemplate restTemplate;
    public DiscordServiceImpl discordAuthorizationService;

    public DiscordAuthSnowflakeGenService discordAuthSnowflakeGenService;
    @Autowired
    public DiscordController(RestTemplate restTemplate, DiscordServiceImpl discordAuthorizationService, DiscordAuthSnowflakeGenService discordAuthSnowflakeGenService){
        this.restTemplate = restTemplate;
        this.discordAuthorizationService = discordAuthorizationService;
        this.discordAuthSnowflakeGenService = discordAuthSnowflakeGenService;
    }

    @Override
    public ResponseEntity<?> authorization(String code) {
        return ResponseEntity.of(Optional.ofNullable(discordAuthSnowflakeGenService.authenticate(code)));

    }
}
