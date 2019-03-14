package pl.mkrupnik.springtraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse {

    private boolean isCommon;
    private double distance;
    private Coordinates middlePoint;
    private GeometryCollection commonGeometryCollection;

}
