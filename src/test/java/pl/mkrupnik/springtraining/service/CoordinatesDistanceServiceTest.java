package pl.mkrupnik.springtraining.service;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.mkrupnik.springtraining.model.Coordinates;

import static org.testng.Assert.*;

public class CoordinatesDistanceServiceTest {

    @DataProvider(name = "coordinates")
    public static Object[][] CoordinatesWithResults() {
        return new Object[][]{
                {new Coordinates(0, 0), new Coordinates(0, 0), 0.0},
                {new Coordinates(1, 0), new Coordinates(1, 0), 0.0},
                {new Coordinates(2, 0), new Coordinates(2, 0), 0.0},
                {new Coordinates(3, 0), new Coordinates(3, 0), 0.0},
                {new Coordinates(4, 2), new Coordinates(4, 2), 0.0},
                {new Coordinates(5, 2), new Coordinates(4, 2), 111.12718798166406},
                {new Coordinates(1, 2), new Coordinates(1, 5), 333.5847799336762},
                {new Coordinates(0.1215, 0.252), new Coordinates(0.5241, 0.12512), 46.93736249268507},
                {new Coordinates(0.12234, 1), new Coordinates(0.122345, 1), 5.558899555922232E-4},
                {new Coordinates(52, 4), new Coordinates(-52, 3), 11534.277685142788}

        };
    }


    @Test(dataProvider = "coordinates")
    public void sameCoordinatesShouldHaveNoDistance(Coordinates coo1 , Coordinates coo2, double expected) {
        CoordinatesDistanceService cds = new CoordinatesDistanceService();
        double result = cds.haversineDistance(coo1, coo2);
        assertEquals(result, expected);
    }
}