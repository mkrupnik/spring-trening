package pl.mkrupnik.springtraining.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class LoggerService {

    Logger logger = LoggerFactory.getLogger(LoggerService.class);

    public void sendInfo(String msg) {
        logger.info(msg);
    }
}
