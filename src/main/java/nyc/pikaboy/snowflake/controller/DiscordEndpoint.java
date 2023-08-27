package nyc.pikaboy.snowflake.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/")
public interface DiscordEndpoint {
    @GetMapping("authorize/")
    ResponseEntity<?> authorization(@RequestParam String code);
}
