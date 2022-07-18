package practiceApp.userauth.components;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import practiceApp.userauth.users.User;
import practiceApp.userauth.users.UserRepository;

@Controller
public class DataController {

    @Autowired
    private UserRepository usersRepo;

    @GetMapping("/verify")
    @ResponseBody
    public String getRandomSquareInfo() {
        return "verified";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> listAll() {
        return usersRepo.findAll();
    }
}
