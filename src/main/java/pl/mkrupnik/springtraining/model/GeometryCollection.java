package pl.mkrupnik.springtraining.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

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
        List<List<Double>> coordinates = findMostDistantCoordinates();
//        List<Double> coo1 = new ArrayList<>();
//        List<Double> coo2 = new ArrayList<>();
//        coo1.add(4.0);
//        coo1.add(5.0);
//        coo2.add(5.0);
//        coo2.add(6.0);
//        coordinates.add(coo1);
//        coordinates.add(coo2);
        lineString.setCoordinates(coordinates);
        geometries.add(lineString);
    }
    public int maxDistance() {
        return 100;
    }

    private List<List<Double>> findMostDistantCoordinates() {
        double maxDist = Double.NEGATIVE_INFINITY;
        List<List<Double>> result = new ArrayList<>();
        result.add(new ArrayList<Double>());
        result.add(new ArrayList<Double>());
        List<List<Double>> coordinatesToAnalyze = new ArrayList<>();
        for(Feature feature : geometries) {
            coordinatesToAnalyze.addAll(feature.getClearCoordinates());
        }
        int l = coordinatesToAnalyze.size();
        for(int i=0; i<l;i++) {
            for(int j = i+i; j<l;j++) {
                double distance = distance(coordinatesToAnalyze.get(i),coordinatesToAnalyze.get(j));
                if(maxDist < distance) {
                    maxDist = distance;
                    result.set(0,coordinatesToAnalyze.get(i));
                    result.set(1,coordinatesToAnalyze.get(j));
                }
            }
        }
        return result;
    }

    private double distance(List<Double>coo1, List<Double>coo2) {
        double x1 = coo1.get(0);
        double y1 = coo1.get(1);
        double x2 = coo2.get(0);
        double y2 = coo2.get(1);

        return(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }
}
