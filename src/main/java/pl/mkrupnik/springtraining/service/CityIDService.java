package pl.mkrupnik.springtraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mkrupnik.springtraining.exception.NoSuchCityException;
import pl.mkrupnik.springtraining.model.IDMetaData;

import java.net.URI;

@Service
class CityIDService {

    @Value("${idsearch}")
    private String apiQueryUrl;

    @Autowired
    private RestTemplate restTemplate;

    String getId(String cityName) throws NoSuchCityException {
        IDMetaData[] metadataResults = getSearchResult(cityName);
        if (metadataResults.length == 0) throw new NoSuchCityException(String.format("There is no data for %s", cityName));
        return osmIdFromSearchResults(metadataResults);
    }

    private String osmIdFromSearchResults(IDMetaData[] metadataResults) {
        return metadataResults[0].getOsm_id();
    }

    private IDMetaData[] getSearchResult(String cityName) {
        URI uri = UriComponentsBuilder.fromUriString(apiQueryUrl).queryParam("format", "json").queryParam("q", cityName).build().toUri();
        return restTemplate.getForObject(uri, IDMetaData[].class);
    }
}
