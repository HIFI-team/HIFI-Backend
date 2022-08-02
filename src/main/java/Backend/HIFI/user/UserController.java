package Backend.HIFI.user;

import Backend.HIFI.user.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FollowService followService;

    @GetMapping("/profile/{email}")
    @ResponseBody
    public String profilePage(@PathVariable("email") String email) {
        try {
            userService.findByEmail(email);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "profile";
    }

    @GetMapping("/follow")
    @ResponseBody
    public String followPage() { return "follow"; }

//    @GetMapping("/su")
//    public String searchUserPage() { return "su"; }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

