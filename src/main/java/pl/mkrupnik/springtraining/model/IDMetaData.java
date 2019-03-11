package pl.mkrupnik.springtraining.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IDMetaData {
    String osm_type;
    String osm_id;
    String[] boundingbox;
    String lat;
    String lon;
    String display_name;
    String place_id;
}