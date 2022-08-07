package Backend.HIFI.user;

import Backend.HIFI.auth.dto.UserProfileDto;
import Backend.HIFI.auth.dto.UserProfileUpdateDto;
import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FollowService followService;
    private final FollowRepository followRepository;


    // 아직 어떻게 처리해야 하는지 잘 몰라서 임의로 만들고 후에 다듬을 예정
    @PostMapping("/profile/{email}")
    @ResponseBody
    public String profilePage(@PathVariable("email") String email) {
        try {
            // TODO 본인이 프로필 볼 때 + 비공개일때 고려해야 함
            User user = userService.findByEmail(email);
            UserProfileDto userProfileDto = new UserProfileDto().toUserProfileDto(user);

            // TODO 나중에 변경 해야함
            return userProfileDto.toString();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/follow/{followerEmail}_{followingEmail}")
    @ResponseBody
    public String followUser
            (@PathVariable("followerEmail") String followerEmail,
            @PathVariable("followingEmail") String followingEmail) {

        try {
            User follower = userService.findByEmail(followerEmail);
            User following = userService.findByEmail(followingEmail);
            followService.following(follower, following);

            return followerEmail + " 회원이 " + followingEmail + " 회원을 팔로우했습니다.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/follow/{followerEmail}_{followingEmail}")
    @ResponseBody
    public String unfollowUser
            (@PathVariable("followerEmail") String followerEmail,
             @PathVariable("followingEmail") String followingEmail) {
        try {
            User follower = userService.findByEmail(followerEmail);
            User following = userService.findByEmail(followingEmail);

            Long followId = followService.getFollowIdByFollowerAndFollowing(follower, following);

            followRepository.deleteById(followId);


            return followerEmail + " 회원이 " + followingEmail + " 회원을 언팔로우했습니다.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }


    @PostMapping("/followList/{email}")
    @ResponseBody
    public String followPage(@PathVariable("email") String email) {
        User user = userService.findByEmail(email);

        // TODO 프로필 보이도록 코드 추가 해야함
        UserProfileDto userProfileDto = new UserProfileDto().toUserProfileDto(user);

        // TODO 본인이 프로필 볼 때 + 비공개일때 고려해야 함

        List<User> followerList = followService.getFollower(user);
        List<User> followingList = followService.getFollowing(user);

        return "followerList = " + followerList + "\nfollowingList = " + followingList;
    }

    @GetMapping("/update/{email}")
    @ResponseBody
    public String updatePage(@PathVariable("email") String email) { return "update";}

    @PostMapping("/update/{email}")
    @ResponseBody
    public String updateProfile(@PathVariable("email") String email, UserProfileUpdateDto userProfileUpdateDto) {

        // TODO UserProfileUpdateDto 받는 코드 작성 필요
        User user = userService.findByEmail(email);
        user.update(userProfileUpdateDto);

        return "프로필이 업데이트 되었습니다.";
    }

    @GetMapping("/search")
    @ResponseBody
    public String searchPage() {
        return "search";
    }

    // TODO
    //  은근 고려할게 많아서 나중에 해야할 듯
    @PostMapping("/search/{email}_{name}")
    @ResponseBody
    public String setUserSearch(
            @PathVariable("email") String email,
            @PathVariable("name") String name) {
        User user = userService.findByEmail(email);
        userService.userSearch(user, name);


        return email + " 유저가 " + name + "을 검색했습니다.\n검색 리스트 : " + user.getSearchList().toString();
    }

//    @GetMapping("/su")
//    public ResponseEntity<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

