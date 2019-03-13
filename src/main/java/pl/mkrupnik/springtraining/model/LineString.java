package pl.mkrupnik.springtraining.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
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
    private List<List<Double>> coordinates = null;


    void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    @JsonIgnore
    public List<Coordinates> getListOfCoordinates(){
        List<Coordinates> result = new ArrayList<>();
        for(List<Double> list :coordinates) {
            result.add(new Coordinates(list));
        };
        return result;
    }

}
