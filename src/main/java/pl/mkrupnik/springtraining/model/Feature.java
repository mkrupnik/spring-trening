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
public class Feature {
    @JsonProperty("type")
    private String type="Feature";
    @JsonProperty("coordinates")
    private List<List<List<List<Double>>>> coordinates;

    @JsonIgnore
    public List<Coordinates> getListOfCoordinates(){
        List<Coordinates> result = new ArrayList<>();
        for(List<Double> list :coordinates.get(0).get(0)) {
            result.add(new Coordinates(list));
        };
        return result;
    }

}
