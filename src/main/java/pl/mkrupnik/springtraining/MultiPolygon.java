package pl.mkrupnik.springtraining;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "coordinates"
})
public class MultiPolygon {

    @JsonProperty("type")
    public String type;
    @JsonProperty("coordinates")
    public List<List<List<List<String>>>> coordinates = null;

}