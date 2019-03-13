package pl.mkrupnik.springtraining.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        lineString.setCoordinates(coordinates);
        geometries.add(lineString);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeometryCollection that = (GeometryCollection) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(geometries, that.geometries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, geometries);
    }
}
