package pl.mkrupnik.springtraining.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import pl.mkrupnik.springtraining.exception.NoSuchCityException;
import pl.mkrupnik.springtraining.model.GeometryCollection;
import pl.mkrupnik.springtraining.model.IDMetaData;

import java.net.URI;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CityBoundariesServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CityIDService cityIdService;

    @InjectMocks
    private CityBoundariesService cityBoundariesService;

    @Test
    public void shouldReturnIDString() throws NoSuchCityException {

        when(restTemplate.getForObject(any(URI.class), eq(GeometryCollection.class))).thenReturn(prepareCityMetada());

         GeometryCollection geometryCollection = cityBoundariesService.getCityBoundariesGeoJSON(new ArrayList<String>());

        assertNotNull(geometryCollection);
    }

    private GeometryCollection prepareCityMetada() {
        return new GeometryCollection();
    }

}