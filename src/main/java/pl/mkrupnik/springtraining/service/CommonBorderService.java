package pl.mkrupnik.springtraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkrupnik.springtraining.model.CommonResponse;
import pl.mkrupnik.springtraining.model.Coordinates;
import pl.mkrupnik.springtraining.model.GeometryCollection;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonBorderService {

    @Autowired
    private CollectAllCoordinatesService collectAllCoordinatesService;

    @Autowired
    private CoordinatesDistanceService coordinatesDistanceService;

    @Autowired
    private AddLineService addLineService;

    public boolean hasCommonBorder(GeometryCollection geometryCollection1, GeometryCollection geometryCollection2) {
        List<Coordinates> listCity1 = collectAllCoordinatesService.getCoordinates(geometryCollection1);
        List<Coordinates> listCity2 = collectAllCoordinatesService.getCoordinates(geometryCollection2);
        for (Coordinates coordinates : listCity1) {
            if (listCity2.contains(coordinates)) return true;
        }
        return false;
    }

    public CommonResponse commonBorder(GeometryCollection geometryCollection1, GeometryCollection geometryCollection2) {
        List<Coordinates> listCity1 = collectAllCoordinatesService.getCoordinates(geometryCollection1);
        List<Coordinates> listCity2 = collectAllCoordinatesService.getCoordinates(geometryCollection2);
        List<Coordinates> listOfCommonCoordinates = new ArrayList<>();
        boolean flag = false;
        double distance = 0.0;
        Coordinates last = null;
        for (Coordinates coordinates : listCity1) {
            if (listCity2.contains(coordinates)) {
                flag = true;
                if (last != null) distance = distance + coordinatesDistanceService.haversineDistance(coordinates,last);
                listOfCommonCoordinates.add(coordinates);
                last = coordinates;
            }
        }
        GeometryCollection gc = addLineService.createLineFromCoordinates(listOfCommonCoordinates);
        Coordinates middleOfBorder = middleFromCoordinatesList(listOfCommonCoordinates, distance);
        return new CommonResponse(flag, distance, middleOfBorder, gc);
    }

    private Coordinates middleFromCoordinatesList(List<Coordinates> listOfCoordinates, double distance) {
        double midDistance = distance/2.0;
        Coordinates cooM1 = null;
        Coordinates cooM2 = null;
        double checkDistance1 =0.0;
        double checkDistance2 = 0.0;
        for(int i = 1; checkDistance2<midDistance; i++) {
            cooM2 = listOfCoordinates.get(i);
            cooM1 = listOfCoordinates.get(i-1);
            checkDistance1 = checkDistance2;
            checkDistance2 += coordinatesDistanceService.
                    haversineDistance(cooM2,cooM1);
        }
        double ratio = (checkDistance2-midDistance)/(checkDistance2-checkDistance1);
        return new Coordinates(cooM2.getX()*(1-ratio)+cooM1.getX()*ratio,cooM2.getY()*(1-ratio)+cooM1.getY()*ratio);


    }
}
