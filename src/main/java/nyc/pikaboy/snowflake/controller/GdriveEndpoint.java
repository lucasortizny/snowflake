package nyc.pikaboy.snowflake.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gdrive")
public interface GdriveEndpoint {
    @PostMapping("/upload")
    ResponseEntity<?> uploadFile();
}
