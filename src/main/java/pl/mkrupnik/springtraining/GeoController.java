package pl.mkrupnik.springtraining;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoController {

    private AvailableCities aviCities = new AvailableCities();
    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/search")
    public GeoConsummer greeting(@RequestParam(value="city") String name) {
        return new GeoConsummer(aviCities.getCityID(template),
                String.format(template, name));
    }
}
