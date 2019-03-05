package pl.mkrupnik.springtraining;

import java.util.HashMap;
import java.util.Map;

class AvailableCities {

    private Map<String,Integer> cityMap;

    AvailableCities() {
        cityMap = new HashMap<>();
        cityMap.put("Warsaw", 336074);
    }

    int getCityID(String city) {
        return cityMap.get(city);
    }
}
