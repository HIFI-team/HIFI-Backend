package Backend.HIFI.user;

import Backend.HIFI.auth.dto.*;
import Backend.HIFI.common.redis.RedisService;
import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FollowService followService;
    private final FollowRepository followRepository;
    private final RedisService redisService;


    @ApiOperation(value = "마이프로필 요청")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> profilePage(Authentication auth) {
        User user = userService.findByAuth(auth);
        UserDto userDto = new UserDto().toUserDto(user);

        return ResponseEntity.ok(userDto);
    }

    @ApiOperation(value = "프로필 요청")
    @PostMapping("/profile")
    public ResponseEntity<UserDto> profilePage(@RequestBody String email, Authentication auth) {
        User toUser = userService.findByEmail(email);
        User fromUser = userService.findByAuth(auth);

        UserDto userDto = new UserDto().toUserDto(toUser);
        if (!userService.canWatchReview(fromUser, toUser)) {
            userDto.setReviewList(null);
        }
        return ResponseEntity.ok(userDto);
    }

    @ApiOperation(value = "프로필 업데이트 요청")
    @PostMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDto userProfileDto, Authentication auth) {

        User user = userService.findByAuth(auth);
        userService.updateProfile(user, userProfileDto);

        return ResponseEntity.ok("프로필 업데이트 완료");
    }

    @ApiOperation(value = "팔로우 요청")
    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestBody String followingEmail, Authentication auth) {

        User follower = userService.findByAuth(auth);
        User following = userService.findByEmail(followingEmail);
        followService.following(follower, following);

        return ResponseEntity.ok("팔로우 완료");
    }

    @ApiOperation(value = "언팔로우 요청")
    @DeleteMapping("/follow")
    public ResponseEntity<String> unfollowUser(@RequestBody String followingEmail, Authentication auth) {

        User follower = userService.findByAuth(auth);
        User following = userService.findByEmail(followingEmail);

        Long followId = followService.getFollowIdByFollowerAndFollowing(follower, following);

        followRepository.deleteById(followId);

        return ResponseEntity.ok("언팔로우 완료");
    }


    @ApiOperation(value = "팔로우 리스트 요청")
    @PostMapping("/followList")
    public String followPage(@RequestBody String email, Authentication auth) {
        User user = userService.findByEmail(email);

        // TODO 프로필 보이도록 코드 추가 해야함
        UserProfileDto userProfileDto = new UserProfileDto().toUserProfileDto(user);

        // TODO 본인이 프로필 볼 때 + 비공개일때 고려해야 함

        List<User> followerList = followService.getFollower(user);
        List<User> followingList = followService.getFollowing(user);

        return "followerList = " + followerList + "\nfollowingList = " + followingList;
    }


    @ApiOperation(value = "회원 탈퇴")
    @GetMapping("/delete")
    public String deletePage(Authentication auth) {
        User user = userService.findByAuth(auth);
        userService.deleteUser(user);

        return user.getEmail() + " was deleted";
    }

    // TODO
    //  은근 고려할게 많아서 나중에 해야할 듯
    @ApiOperation(value = "유저 검색")
    @PostMapping("/search")
    public String setUserSearch(@RequestBody String name, Authentication auth) {
        User user = userService.findByAuth(auth);
        userService.userSearch(user, name);

        return user.getEmail() + " 유저가 " + name + "을 검색했습니다.\n검색 리스트 : " + user.getSearchList().toString();
    }

//    @GetMapping("/su")
//    public ResponseEntity<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

