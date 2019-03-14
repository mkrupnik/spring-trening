package pl.mkrupnik.springtraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkrupnik.springtraining.model.Coordinates;

import java.util.Arrays;
import java.util.List;

@Service
public class MaxDistanceService {

    @Autowired
    private CoordinatesDistanceService coordinatesDistanceService;

    public List<Coordinates> findMostDistantCoordinates(List<Coordinates> listOfAllCoordinates) {
        double maxDist = Double.NEGATIVE_INFINITY;
        List<Coordinates> result = Arrays.asList(new Coordinates(), new Coordinates());
        for (int i = 0; i < listOfAllCoordinates.size(); i++) {
            for (int j = i + 1; j < listOfAllCoordinates.size(); j++) {
                Coordinates coordinates1 = listOfAllCoordinates.get(i);
                Coordinates coordinates2 = listOfAllCoordinates.get(j);
                double distance = coordinatesDistanceService.haversineDistance(coordinates1, coordinates2);
                if (maxDist < distance) {
                    maxDist = distance;
                    result.set(0, coordinates1);
                    result.set(1, coordinates2);
                }
            }
        }
        return result;
    }

    public double findLongestDistance(List<Coordinates> listOfAllCoordinates) {
        double maxDist = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < listOfAllCoordinates.size(); i++) {
            for (int j = i + 1; j < listOfAllCoordinates.size(); j++) {
                Coordinates coordinates1 = listOfAllCoordinates.get(i);
                Coordinates coordinates2 = listOfAllCoordinates.get(j);
                double distance = coordinatesDistanceService.haversineDistance(coordinates1, coordinates2);
                if (maxDist < distance) {
                    maxDist = distance;
                }
            }
        }
        return maxDist;
    }
}
