package pl.mkrupnik.springtraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates{

    private double x = 0;
    private double y = 0;

    Coordinates(List<Double> list) {
        this.x = list.get(0);
        this.y = list.get(1);
    }
    public List<Double> asList() {
        return Arrays.asList(x,y);
    }
}
