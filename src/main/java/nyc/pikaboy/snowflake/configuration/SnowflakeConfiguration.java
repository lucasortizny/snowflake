package nyc.pikaboy.snowflake.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SnowflakeConfiguration {
    @Value("${discord.properties.baseUri}")
    public String discordUri;
    @Autowired
    RestTemplateBuilder builder;


    @Bean
    public RestTemplate restTemplate(){
        return this.builder
                .rootUri(discordUri)
                .build();
    }
}
