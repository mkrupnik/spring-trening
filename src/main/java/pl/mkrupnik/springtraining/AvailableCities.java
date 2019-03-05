package pl.mkrupnik.springtraining;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class AvailableCities {

    private Map<String,Integer> cityMap;

    AvailableCities() {
        cityMap = new HashMap<>();
        cityMap.put("Warszawa", 336074);
        cityMap.put("Sulejówek", 2910917);
        cityMap.put("Ząbki", 2910919);
        cityMap.put("Legionowo", 3014990);
        cityMap.put("Łomianki", 336312);
        cityMap.put("Piaseczno", 336134);
    }

    int getCityID(String city) {
        return cityMap.get(city);
    }
}
