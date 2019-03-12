package pl.mkrupnik.springtraining;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@SpringBootApplication
@Slf4j
public class SpringtrainingApplication {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        MappingJackson2HttpMessageConverter mapper =
                new MappingJackson2HttpMessageConverter();
        mapper.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8,
                MediaType.TEXT_PLAIN, MediaType.TEXT_HTML));
        template.getMessageConverters().add(mapper);
        return template;
    }

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(SpringtrainingApplication.class, args);
        log.info("Let's roll!");
    }

}
