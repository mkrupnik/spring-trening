package pl.mkrupnik.springtraining.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import pl.mkrupnik.springtraining.exception.NoSuchCityException;
import pl.mkrupnik.springtraining.model.IDMetaData;

import java.net.URI;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CityIDServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CityIDService cityIdService;

    @Test
    public void shouldReturnIDString() throws NoSuchCityException {
        // given
        ReflectionTestUtils.setField(cityIdService, "apiQueryUrl", "localhost");
        when(restTemplate.getForObject(any(URI.class), eq(IDMetaData[].class))).thenReturn(prepareCityMetada());

        // when
        String krakowID = cityIdService.getId("krakow");

        // then
        assertEquals("666", krakowID);
    }

    private IDMetaData[] prepareCityMetada() {
        IDMetaData krakow = new IDMetaData();
        krakow.setBoundingbox(new String[]{"", ""});
        krakow.setOsm_id("666");
        return new IDMetaData[]{krakow};
    }

}