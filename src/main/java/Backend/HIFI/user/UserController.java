package Backend.HIFI.user;

import Backend.HIFI.auth.dto.UserProfileDto;
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
            // 본인일 프로필 볼 때도 고려해야 함
            User user = userService.findByEmail(email);
            UserProfileDto userProfileDto = new UserProfileDto().toUserProfileDto(user);
            return "abcd";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/follow")
    @ResponseBody
    public String followPage() {

        return "follow";
    }

//    @GetMapping("/su")
//    public ResponseEntity<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

