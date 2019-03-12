package pl.mkrupnik.springtraining.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrupnik.springtraining.model.GeometryCollection;
import pl.mkrupnik.springtraining.service.CityBoundariesService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class GeoController {

    @Autowired
    private CityBoundariesService cityBoundariesService;

    @RequestMapping("/search")
    public GeometryCollection searchController(HttpServletRequest request, @RequestParam(value = "city") List<String> requestedCities) throws IOException {
        log.info(String.format("User %s requested GeoJSON for cities: %s", request.getRemoteUser(), String.join(", ", requestedCities)));
        return cityBoundariesService.getCityBoundariesGeoJSON(requestedCities);
    }
}
