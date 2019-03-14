package pl.mkrupnik.springtraining.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mkrupnik.springtraining.exception.NoSuchCityException;
import pl.mkrupnik.springtraining.model.GeometryCollection;

import java.net.URI;
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
}
