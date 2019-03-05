package pl.mkrupnik.springtraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoController {

    @Autowired
    private AvailableCities availableCities;

    @RequestMapping("/search")
    public String geoController(@RequestParam(value="city") String name) {
        int id = availableCities.getCityID(name);
        return GeoConsummer.getGJSON(id);
    }
}
