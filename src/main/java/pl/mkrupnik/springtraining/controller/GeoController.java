package pl.mkrupnik.springtraining.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrupnik.springtraining.model.CommonResponse;
import pl.mkrupnik.springtraining.model.Coordinates;
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
        return cityBoundariesService.getGeometryCollection(requestedCities);
    }

    @RequestMapping("/searchdist")
    public GeometryCollection searchWithDistanceController(HttpServletRequest request, @RequestParam(value = "city") List<String> requestedCities) throws IOException {
        log.info(String.format("User %s requested GeoJSON with distance for cities: %s", request.getRemoteUser(), String.join(", ", requestedCities)));
        return cityBoundariesService.getGeometryCollectionWithDistance(requestedCities);
    }

    @RequestMapping("/distance")
    public double findDistanceController(HttpServletRequest request, @RequestParam(value = "city") List<String> requestedCities) throws IOException {
        log.info(String.format("User %s requested distance for cities: %s", request.getRemoteUser(), String.join(", ", requestedCities)));
        return cityBoundariesService.getMaxDistance(requestedCities);
    }

    @RequestMapping("/iscommon")
    public boolean isCommonBorder(HttpServletRequest request, @RequestParam(value = "city") List<String> requestedCities) throws IOException {
        log.info(String.format("User %s requested boolean common border for cities: %s", request.getRemoteUser(), String.join(", ", requestedCities)));
        return cityBoundariesService.getBooleanIfCommon(requestedCities);
    }

    @RequestMapping("/common")
    public CommonResponse searchCommonBorder(HttpServletRequest request, @RequestParam(value = "city") List<String> requestedCities) throws IOException {
        log.info(String.format("User %s requested common border for cities: %s", request.getRemoteUser(), String.join(", ", requestedCities)));
        return cityBoundariesService.getCommonCoordinates(requestedCities);
    }
}
