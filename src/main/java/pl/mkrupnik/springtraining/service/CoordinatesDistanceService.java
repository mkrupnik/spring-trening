package pl.mkrupnik.springtraining.service;

import org.springframework.stereotype.Service;
import pl.mkrupnik.springtraining.model.Coordinates;

@Service
public class CoordinatesDistanceService {



    public double haversineDistance(Coordinates coo1, Coordinates coo2) {
        double x1 = Math.toRadians(coo1.getX());
        double y1 = Math.toRadians(coo1.getY());
        double x2 = Math.toRadians(coo2.getX());
        double y2 = Math.toRadians(coo2.getY());
        double r = 6371.0;

        return(2 * r * Math.asin(Math.sqrt(Math.pow(Math.sin((y2-y1)/2),2) + Math.cos(y1)*Math.cos(y2)*Math.pow(Math.sin((x2-x1)/2),2))));
    }

    public double haversineDistanceDouble(double x1, double y1, double x2, double y2) {
        x1 = Math.toRadians(x1);
        x2 = Math.toRadians(x2);
        y1 = Math.toRadians(y1);
        y2 = Math.toRadians(y2);
        double r = 6371.0;
        return 2 * r * Math.asin(Math.sqrt(Math.pow(Math.sin((y2-y1)/2),2) + Math.cos(y1)*Math.cos(y2)*Math.pow(Math.sin((x2-x1)/2),2)));
    }

    //Only for flatearthers
    @Deprecated
    public double distance(Coordinates coo1, Coordinates coo2) {
        double x1 = coo1.getX();
        double y1 = coo1.getY();
        double x2 = coo2.getX();
        double y2 = coo2.getY();

        return (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static void main(String[] args) {
        CoordinatesDistanceService cd = new CoordinatesDistanceService();
        System.out.println(cd.haversineDistanceDouble(21.2708025, 52.1685179, 20.8763457, 52.3148807));
    }
}
