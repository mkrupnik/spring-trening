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
public class MultiPolygon {

    @JsonProperty("type")
    public String type;
    @JsonProperty("coordinates")
    public List<List<List<List<Double>>>> coordinates = null;

}