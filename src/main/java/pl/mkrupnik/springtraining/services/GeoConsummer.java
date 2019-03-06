package pl.mkrupnik.springtraining.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.mkrupnik.springtraining.geoobjects.GeometryCollection;

@Service
public class GeoConsummer {

    private final String urlPath;

    public GeoConsummer(@Value("${url.path}") String urlPath) {
        this.urlPath = urlPath;
    }

    public String getGJSON(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(urlPath, id), String.class);
    }

    public GeometryCollection getColGJSON(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(urlPath, id), GeometryCollection.class);
    }
}