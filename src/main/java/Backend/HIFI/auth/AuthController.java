package Backend.HIFI.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    @GetMapping("/register")
    @ResponseBody
    public String register() {
        return "register";
    }
}
