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
public class LineString extends Feature {

    @JsonProperty("type")
    private String type = "LineString";
    @JsonProperty("coordinates")
    public List<List<Double>> coordinates = null;


    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public List<List<Double>> getClearCoordinates(){
        return coordinates;
    }

}
