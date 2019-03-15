package pl.mkrupnik.springtraining.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.mkrupnik.springtraining.model.Coordinates;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MaxDistanceServiceTest {
    @Mock
    private CoordinatesDistanceService coordinatesDistanceService;

    @InjectMocks
    private MaxDistanceService maxDistanceService;

    @Test
    public void shouldReturnMaxDistanceAsMocked() {
        // given

        when(coordinatesDistanceService.haversineDistance(any(Coordinates.class), any(Coordinates.class))).
                thenReturn(100.0);
        // when
        double maxDistance = maxDistanceService
                .findLongestDistance(Arrays.asList(new Coordinates(0, 1), new Coordinates(1, 0)));
        // then
        assertEquals(100.0, maxDistance);
    }

    @Test
    public void shouldReturnNotNullListOfCoordinates() {
        // given

        when(coordinatesDistanceService.haversineDistance(any(Coordinates.class), any(Coordinates.class))).
                thenReturn(Math.random() * 100.0);
        // when
        List<Coordinates> maxDistance = maxDistanceService
                .findMostDistantCoordinates(Arrays.asList(
                        new Coordinates(0, 1),
                        new Coordinates(1, 0),
                        new Coordinates(1, 5),
                        new Coordinates(3, 2),
                        new Coordinates(1, 10)));
        // then
        assertNotNull(maxDistance.get(0));
        assertNotNull(maxDistance.get(1));
    }


}