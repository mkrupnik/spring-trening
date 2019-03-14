package pl.mkrupnik.springtraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkrupnik.springtraining.model.Coordinates;
import pl.mkrupnik.springtraining.model.GeometryCollection;
import pl.mkrupnik.springtraining.model.LineString;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddLineService {

    @Autowired
    private CollectAllCoordinatesService collectAllCoordinatesService;

    @Autowired
    private MaxDistanceService maxDistanceService;

    public void addLongestLine(GeometryCollection geometryCollection) {
        LineString lineString = new LineString();
        List<Coordinates> coordinates = maxDistanceService
                .findMostDistantCoordinates(collectAllCoordinatesService.getCoordinates(geometryCollection));
        List<List<Double>> listCoordinates = coordinates.stream().map(Coordinates::asList).collect(Collectors.toList());
        lineString.setCoordinates(listCoordinates);
        geometryCollection.getGeometries().add(lineString);
    }

    public GeometryCollection createLineFromCoordinates(List<Coordinates> listOfCoordinates) {
        LineString lineString = new LineString();
        List<List<Double>> listCoordinates = listOfCoordinates.stream().
                map(Coordinates::asList).collect(Collectors.toList());
        lineString.setCoordinates(listCoordinates);
        GeometryCollection geometryCollection = new GeometryCollection();
        geometryCollection.getGeometries().add(lineString);
        return geometryCollection;
    }
}
