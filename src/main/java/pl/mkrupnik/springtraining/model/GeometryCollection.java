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
    public List<MultiPolygon> geometries = new ArrayList<>();

    @JsonProperty("geometries")
    public void add(List<MultiPolygon> multiPolygon) {
        geometries.addAll(multiPolygon);
    }

    @JsonProperty("geometries")
    public List<MultiPolygon> getMultiPolygon() {
        return geometries;
    }

}
