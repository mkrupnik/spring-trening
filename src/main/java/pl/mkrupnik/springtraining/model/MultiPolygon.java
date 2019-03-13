package pl.mkrupnik.springtraining.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "coordinates"
})
public class MultiPolygon extends Feature {

    @JsonProperty("type")
    private String type="MultiPolygon";
    @JsonProperty("coordinates")
    private List<List<List<List<Double>>>> coordinates = null;

    public List<List<List<List<Double>>>> getCoordinates() {
        return coordinates;
    }


    @JsonIgnore
    public List<Coordinates> getListOfCoordinates(){
        List<Coordinates> result = new ArrayList<>();
        for(List<Double> list :coordinates.get(0).get(0)) {
            result.add(new Coordinates(list));
        };
        return result;
    }

}