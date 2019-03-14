package pl.mkrupnik.springtraining.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

}
