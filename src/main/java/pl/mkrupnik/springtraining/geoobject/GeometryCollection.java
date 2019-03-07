package pl.mkrupnik.springtraining.geoobject;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "type",
        "geometries"
})

public class GeometryCollection {

    @JsonProperty("type")
    private String type="GeometryCollection";
    @JsonProperty("geometries")
    public List<MultiPolygon> geometries = new ArrayList<>();

    @JsonProperty("geometries")
    public void add(List<MultiPolygon> multiPolygon){
        geometries.addAll(multiPolygon);
    }

    @JsonProperty("geometries")
    public List<MultiPolygon> getMultiPolygon() {
        return geometries;
    }

}
