package pl.mkrupnik.springtraining.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrupnik.springtraining.Services.GeoConsummer;
import pl.mkrupnik.springtraining.GeoObjects.GeometryCollection;

import java.io.IOException;
import java.util.List;

@RestController
public class GeoController {

    @Autowired
    private AvailableCities availableCities;

    @Autowired
    private GeoConsummer geoConsummer;

    @RequestMapping("/search")
    public String geoController(@RequestParam(value="city") List<String> params) throws IOException {
        GeometryCollection result = new GeometryCollection();
        ObjectMapper mapper = new ObjectMapper();
            for (String s : params) {
                int id = availableCities.getCityID(s);
                GeometryCollection toAdd = mapper.readValue(geoConsummer.getGJSON(id), GeometryCollection.class);
                result.add(toAdd.getMultiPolygon());
            }
        return mapper.writeValueAsString(result);
    }
}
