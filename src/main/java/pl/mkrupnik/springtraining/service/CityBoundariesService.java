package pl.mkrupnik.springtraining.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mkrupnik.springtraining.exception.NoSuchCityException;
import pl.mkrupnik.springtraining.model.CommonResponse;
import pl.mkrupnik.springtraining.model.Coordinates;
import pl.mkrupnik.springtraining.model.GeometryCollection;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CityBoundariesService {

    @Autowired
    private CityIDService cityIDService;

    @Autowired
    private AddLineService addLineService;

    @Autowired
    private GetMaxDistanceService getMaxDistanceService;

    @Autowired
    private CommonBorderService commonBorderService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${geojson.search}")
    private String geojsonApiQuery;

    public GeometryCollection getGeometryCollection(List<String> requestedCities) {
        GeometryCollection geometryCollection = new GeometryCollection();
        for (String cityName : requestedCities) {
            try {
                String cityId = cityIDService.getId(cityName);
                GeometryCollection toAdd = getCityBoundaries(cityId);
                geometryCollection.add(toAdd.getFeature());
            } catch (NoSuchCityException e) {
                log.info(e.getMessage());
            }
        }
        return geometryCollection;
    }

    public GeometryCollection getGeometryCollectionWithDistance(List<String> requestedCities) {
        GeometryCollection geometryCollection = new GeometryCollection();
        for (String cityName : requestedCities) {
            try {
                String cityId = cityIDService.getId(cityName);
                GeometryCollection toAdd = getCityBoundaries(cityId);
                addLineService.addLongestLine(toAdd);
                geometryCollection.add(toAdd.getFeature());
            } catch (NoSuchCityException e) {
                log.info(e.getMessage());
            }
        }
        addLineService.addLongestLine(geometryCollection);
        return geometryCollection;
    }

    private GeometryCollection getCityBoundaries(String cityId) {
        URI uri = UriComponentsBuilder.fromUriString(geojsonApiQuery).queryParam("id", cityId).build().toUri();
        return restTemplate.getForObject(uri, GeometryCollection.class);
    }

    public double getMaxDistance(List<String> requestedCities) {
        GeometryCollection geometryCollection = new GeometryCollection();
        for (String cityName : requestedCities) {
            try {
                String cityId = cityIDService.getId(cityName);
                GeometryCollection toAdd = getCityBoundaries(cityId);
                geometryCollection.add(toAdd.getFeature());
            } catch (NoSuchCityException e) {
                log.info(e.getMessage());
            }
        }
        return getMaxDistanceService.distance(geometryCollection);
    }

    public boolean getBooleanIfCommon(List<String> requestedCities) {
        if(requestedCities.size()!=2) throw new IllegalArgumentException("Should be run with 2 cities");
        try {
            String cityId1 = cityIDService.getId(requestedCities.get(0));
            String cityId2 = cityIDService.getId(requestedCities.get(1));
            GeometryCollection geometryCollection1 = getCityBoundaries(cityId1);
            GeometryCollection geometryCollection2 = getCityBoundaries(cityId2);
            return commonBorderService.hasCommonBorder(geometryCollection1,geometryCollection2);
        } catch (NoSuchCityException e) {
            log.info(e.getMessage());
        }
        return false;
    }

    public CommonResponse getCommonCoordinates(List<String> requestedCities) {
        if(requestedCities.size()!=2) throw new IllegalArgumentException("Should be run with 2 cities");
        try {
            String cityId1 = cityIDService.getId(requestedCities.get(0));
            String cityId2 = cityIDService.getId(requestedCities.get(1));
            GeometryCollection geometryCollection1 = getCityBoundaries(cityId1);
            GeometryCollection geometryCollection2 = getCityBoundaries(cityId2);
            return commonBorderService.commonBorder(geometryCollection1,geometryCollection2);
        } catch (NoSuchCityException e) {
            log.info(e.getMessage());
        }

        return new CommonResponse(false, 0.0, null, null);
    }
}
