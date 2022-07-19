package practiceApp.userauth.components;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import practiceApp.userauth.users.User;
import practiceApp.userauth.users.UserRepository;

@Controller
public class DataController {

    @Autowired
    private UserRepository usersRepo;

    private ShapeService shapeService = new ShapeService();

    @GetMapping("/verify")
    @ResponseBody
    public String verify() {
        return "verified";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> listAll() {
        return usersRepo.findAll();
    }

    @GetMapping("shape/randomsquare")
    @ResponseBody
    public String getRandomSquare() {
        return shapeService.getRandomSquare();
    }

    @GetMapping("shape/square")
    @ResponseBody
    public String getSquareDetails(@RequestParam Double side) {
        return shapeService.getSquareInfo(side);
    }

    @GetMapping("shape/circle")
    @ResponseBody
    public String getCircleDetails(@RequestParam Double radius) {
        return shapeService.getCircleInfo(radius);
    }

    @GetMapping("shape/triangle")
    @ResponseBody
    public String getTriangleDetails(@RequestParam List<Double> sides) {
        return shapeService.getTriangleInfo(sides);
    }

}
