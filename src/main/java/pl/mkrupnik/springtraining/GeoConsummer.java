package pl.mkrupnik.springtraining;

import org.springframework.web.client.RestTemplate;

public class GeoConsummer {


    //JSON jako obiekt
    public static String getGJSON(int id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://polygons.openstreetmap.fr/get_geojson.py?id="+id+"&params=0";
        String geojson = restTemplate.getForObject(url, String.class);
        return geojson;
    }
}