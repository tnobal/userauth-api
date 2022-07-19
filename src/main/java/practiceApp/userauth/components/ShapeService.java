package practiceApp.userauth.components;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

//import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ShapeService {

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private HttpEntity<String> httpEntity;
    private String baseUrl;

    public ShapeService() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("key", "ValidApiKey");
        httpEntity = new HttpEntity<>(headers);
        baseUrl = "http://localhost:8092/";
    }

//restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
    public String getRandomSquare() {
        return restTemplate.exchange(baseUrl+"randomsquare", HttpMethod.GET, httpEntity, String.class).getBody();
    }

    public String getSquareInfo(Double side) {
        validateInput(side);
        return restTemplate.exchange(baseUrl+"square?side="+side, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    public String getCircleInfo(Double radius) {
        validateInput(radius);
        return restTemplate.exchange(baseUrl+"circle?radius="+radius, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    public String getTriangleInfo(List<Double> sides) {
        validateTriangleLengths(sides);
        return restTemplate.exchange(baseUrl+"triangle?sides="+formattedTriangleSides(sides), HttpMethod.GET, httpEntity, String.class).getBody();
    }

    private static String formattedTriangleSides(List<Double> sides) {
        String formatted = "";
        for (Double side : sides) {
            formatted+=side+",";
        }
        return formatted.substring(0, formatted.length()-2);
    }

    private static void validateTriangleLengths(List<Double> sides) {
        if (sides.size() != 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        double a = sides.get(0);
        double b = sides.get(1);
        double c = sides.get(2);
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (a+b < c || a+c < b || b+c < a) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private static void validateInput(Double input) {
        if (input <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
