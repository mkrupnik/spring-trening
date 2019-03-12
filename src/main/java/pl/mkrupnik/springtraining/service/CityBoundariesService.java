package pl.mkrupnik.springtraining.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mkrupnik.springtraining.model.GeometryCollection;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CityBoundariesService {

    @Autowired
    private CityIDService cityIDService;

    @Value("${geojsonSearch}")
    private String geojsonApiQuery;

    private RestTemplate template = createAndSetupRestTempate();

    public GeometryCollection getCityBoundariesGeoJSON(List<String> requestedCities) {
        GeometryCollection geometryCollection = new GeometryCollection();
        String cityId;
        for (String cityName : requestedCities) {
            try {
                cityId = cityIDService.getIdFromApi(cityName);
                GeometryCollection toAdd = getCityBoundaries(cityId);
                toAdd.addLongestLine();
                geometryCollection.add(toAdd.getFeature());
            } catch (NoSuchCityException e) {
                log.info(e.getMessage());
            }
        }
        geometryCollection.addLongestLine();
        return geometryCollection;
    }

    private GeometryCollection getCityBoundaries(String cityId) {
        URI uri = UriComponentsBuilder.fromUriString(geojsonApiQuery).queryParam("id", cityId).build().toUri();
        return template.getForObject(uri, GeometryCollection.class);
    }

    private RestTemplate createAndSetupRestTempate() {
        RestTemplate template = new RestTemplate();
        MappingJackson2HttpMessageConverter mapper =
                new MappingJackson2HttpMessageConverter();
        mapper.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML));
        template.getMessageConverters().add(mapper);
        return template;
    }
}
