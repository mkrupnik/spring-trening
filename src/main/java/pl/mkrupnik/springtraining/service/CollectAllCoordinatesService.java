package pl.mkrupnik.springtraining.service;

import org.springframework.stereotype.Service;
import pl.mkrupnik.springtraining.model.Coordinates;
import pl.mkrupnik.springtraining.model.Feature;
import pl.mkrupnik.springtraining.model.GeometryCollection;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectAllCoordinatesService {

    public List<Coordinates> getCoordinates(GeometryCollection geometryCollection) {
        List<Coordinates> coordinatesToAnalyze = new ArrayList<>();
        for (Feature feature : geometryCollection.getFeature()) {
            coordinatesToAnalyze.addAll(feature.getListOfCoordinates());
        }
        return coordinatesToAnalyze;
    }
}
