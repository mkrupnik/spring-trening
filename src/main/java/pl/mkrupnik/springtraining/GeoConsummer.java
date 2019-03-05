package pl.mkrupnik.springtraining;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoConsummer {

    private final String urlPath;

    public GeoConsummer(@Value("${url.path}") String urlPath) {
        this.urlPath = urlPath;
    }
    //JSON jako obiekt
    public String getGJSON(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(urlPath, id), String.class);
    }
}