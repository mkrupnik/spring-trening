package pl.mkrupnik.springtraining.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mkrupnik.springtraining.model.IDMetaData;

import java.net.URI;

@Service
public class CityIDService {

    final static String NO_CITY_FOUND = "0";
    @Value("${idsearch}")
    private String apiQueryUrl;

    String getIdFromApi(String cityName) {
        IDMetaData[] metadataResults = getSearchResult(cityName);
        if (metadataResults.length == 0) throw new IllegalArgumentException();
        return osmIdFromSearchResults(metadataResults);
    }

    private String osmIdFromSearchResults(IDMetaData[] metadataResults) {
        return metadataResults[0].getOsm_id();
    }

    private IDMetaData[] getSearchResult(String cityName) {
        URI uri = UriComponentsBuilder.fromUriString(apiQueryUrl).queryParam("format", "json").queryParam("q", cityName).build().toUri();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, IDMetaData[].class);
    }
}
