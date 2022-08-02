package Backend.HIFI.user;

import Backend.HIFI.user.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
            User user = userService.findByEmail(email);
            String description = user.getDescription();
            String image = user.getImage();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "profile";
    }

    @GetMapping("/follow")
    @ResponseBody
    public String followPage() { return "follow"; }

//    @GetMapping("/su")
//    public ResponseEntity<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

