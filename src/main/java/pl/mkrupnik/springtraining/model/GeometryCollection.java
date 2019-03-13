package pl.mkrupnik.springtraining.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({
        "type",
        "geometries"
})

public class GeometryCollection {

    @JsonProperty("type")
    private String type = "GeometryCollection";
    @JsonProperty("geometries")
    public List<Feature> geometries = new ArrayList<>();

    @JsonProperty("geometries")
    public void add(List<? extends Feature> multiPolygon) {
        geometries.addAll(multiPolygon);
    }

    @JsonProperty("geometries")
    public List<? extends Feature> getFeature() {
        return geometries;
    }

    public void addLongestLine() {
        LineString lineString = new LineString();
        List<Coordinates> coordinates = findMostDistantCoordinates();
        List<List<Double>> listCoordinates = coordinates.stream().map(coo -> coo.asList()).collect(Collectors.toList());
        lineString.setCoordinates(listCoordinates);
        geometries.add(lineString);
    }

    private List<Coordinates> findMostDistantCoordinates() {
        double maxDist = Double.NEGATIVE_INFINITY;
        List<Coordinates> result = Arrays.asList(new Coordinates(), new Coordinates());
        List<Coordinates> coordinatesToAnalyze = getCoordinates();
        int size = coordinatesToAnalyze.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Coordinates coo1 = coordinatesToAnalyze.get(i);
                Coordinates coo2 = coordinatesToAnalyze.get(j);
                double distance = distance(coo1, coo2);
                if (maxDist < distance) {
                    maxDist = distance;
                    result.set(0, coo1);
                    result.set(1, coo2);
                }
            }
        }
        return result;
    }

    private List<Coordinates> getCoordinates() {
        List<Coordinates> coordinatesToAnalyze = new ArrayList<>();
        for (Feature feature : geometries) {
            coordinatesToAnalyze.addAll(feature.getListOfCoordinates());
        }
        return coordinatesToAnalyze;
    }

    private double distance(Coordinates coo1, Coordinates coo2) {
        double x1 = coo1.getX();
        double y1 = coo1.getY();
        double x2 = coo2.getX();
        double y2 = coo2.getY();

        return (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
