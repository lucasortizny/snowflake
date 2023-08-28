package nyc.pikaboy.snowflake.controller;

import nyc.pikaboy.snowflake.model.discord.TokenResponse;
import nyc.pikaboy.snowflake.service.DiscordAuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@RestController
public class DiscordController implements DiscordEndpoint{
    public RestTemplate restTemplate;
    public DiscordAuthorizationServiceImpl discordAuthorizationService;
    @Autowired
    public DiscordController(RestTemplate restTemplate, DiscordAuthorizationServiceImpl discordAuthorizationService){
        this.restTemplate = restTemplate;
        this.discordAuthorizationService = discordAuthorizationService;
    }

    @Override
    public ResponseEntity<?> authorization(String code) {

        Optional<TokenResponse> response = discordAuthorizationService.exchangeToken(code);
        if (response.isPresent()) {
            return ResponseEntity.of(response);
        }
        else {
            return ResponseEntity.ok("Response of HTTP Interaction came out null");
        }
    }
}
