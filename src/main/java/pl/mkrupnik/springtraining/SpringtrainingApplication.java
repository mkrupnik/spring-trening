package pl.mkrupnik.springtraining;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class SpringtrainingApplication {

	public static void main(String[] args) throws Throwable{
		SpringApplication.run(SpringtrainingApplication.class, args);
		log.info("Let's roll!");
	}

}
