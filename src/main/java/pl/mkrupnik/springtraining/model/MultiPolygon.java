package pl.mkrupnik.springtraining.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "coordinates"
})
public class MultiPolygon extends Feature {

    @JsonProperty("type")
    private String type="MultiPolygon";
    @JsonProperty("coordinates")
    public List<List<List<List<Double>>>> coordinates = null;


    public List<List<List<List<Double>>>> getCoordinates() {
        return coordinates;
    }

    public List<List<Double>> getClearCoordinates(){
        return coordinates.get(0).get(0);
    }

}