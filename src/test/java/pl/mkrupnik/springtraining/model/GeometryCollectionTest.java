package pl.mkrupnik.springtraining.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class GeometryCollectionTest {

    @Test
    public void shouldReturnCorrectListOfCoordinates() {
        //given
        GeometryCollection gc = new GeometryCollection();
        MultiPolygon mp = new MultiPolygon();

        //when
        List<Double> coo1 = Arrays.asList(1.0, 0.0);
        List<Double> coo2 = Arrays.asList(2.0, 3.0);
        List<Double> coo3 = Arrays.asList(4.0, 5.0);
        List<List<List<List<Double>>>> coordinates = new ArrayList<>();
        coordinates.add(new ArrayList<>());
        coordinates.get(0).add(new ArrayList<>());
        coordinates.get(0).get(0).add(coo1);
        coordinates.get(0).get(0).add(coo2);
        coordinates.get(0).get(0).add(coo3);
        mp.setCoordinates(coordinates);
        gc.add(Collections.singletonList(mp));
        gc.addLongestLine();
        List<Feature> geometries =  gc.geometries;
        Feature lineString = gc.geometries.get(1);
        assertTrue(lineString.getClass().equals(LineString.class));
        List<List<Double>> resultCoordinates = lineString.getClearCoordinates();
        assertTrue(resultCoordinates.contains(coo1));
        assertTrue(resultCoordinates.contains(coo3));

    }

}