package pl.mkrupnik.springtraining.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrupnik.springtraining.service.GeoConsummer;
import pl.mkrupnik.springtraining.geoobject.GeometryCollection;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class GeoController {

    @Autowired
    private AvailableCities availableCities;

    @Autowired
    private GeoConsummer geoConsummer;

    @RequestMapping("/search")
    public String geoController(HttpServletRequest request, @RequestParam(value="city") List<String> params) throws IOException {
        GeometryCollection result = new GeometryCollection();
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        String user = request.getRemoteUser();
        sb.append(user + " requested: ");
            for (String s : params) {
                int id = availableCities.getCityID(s);
                GeometryCollection toAdd = mapper.readValue(geoConsummer.getGJSON(id), GeometryCollection.class);
                result.add(toAdd.getMultiPolygon());
                sb.append(s + ", ");
            }
            log.info(sb.toString());
        return mapper.writeValueAsString(result);
    }
}
