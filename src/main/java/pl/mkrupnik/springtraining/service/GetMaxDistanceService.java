package pl.mkrupnik.springtraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkrupnik.springtraining.model.GeometryCollection;

@Service
public class GetMaxDistanceService {
    @Autowired
    private MaxDistanceService maxDistanceService;

    @Autowired
    private CollectAllCoordinatesService collectAllCoordinatesService;

    public double distance(GeometryCollection geometryCollection) {
        return maxDistanceService.findLongestDistance(collectAllCoordinatesService.getCoordinates(geometryCollection));
    }
}
