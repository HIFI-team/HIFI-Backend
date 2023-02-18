package Backend.HIFI.user;

import Backend.HIFI.common.redis.RedisService;
import Backend.HIFI.common.response.CommonApiResponse;
import Backend.HIFI.review.Review;
import Backend.HIFI.user.dto.UserDto;
import Backend.HIFI.user.dto.UserProfileDto;
import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
import Backend.HIFI.user.follow.dto.FollowRequestDto;
import Backend.HIFI.user.search.SearchDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public CommonApiResponse<UserDto> profilePage(Authentication auth) {
        User user = userService.findByAuth(auth);
        UserDto userDto = new UserDto().of(user);
        userDto.setFollower(followService.getFollower(user).size());
        userDto.setFollowing(followService.getFollowing(user).size());

        return CommonApiResponse.of(userDto);
    }

    @ApiOperation(value = "프로필 요청")
    @PostMapping("/profile")
    public CommonApiResponse<UserDto> profilePage(@RequestBody String email, Authentication auth) {
        User toUser = userService.findByEmail(email);
        User fromUser = userService.findByAuth(auth);

        UserDto userDto = new UserDto().of(toUser);
        if (!userService.canWatchReview(fromUser, toUser)) {
            userDto.setReviewList(null);
        }
        return CommonApiResponse.of(userDto);
    }

    @ApiOperation(value = "프로필 업데이트 요청")
    @PostMapping("/update")
    public CommonApiResponse<String> updateProfile(@RequestBody UserProfileDto userProfileDto) {

        // token 못받아와서 userProfileDto email 추가하여 이용
//        User user = userService.findByAuth(auth);
        User user = userService.findByEmail(userProfileDto.getEmail());
        userService.updateProfile(user, userProfileDto);

        System.out.println("***************\n"+userProfileDto);
        return CommonApiResponse.of("프로필 업데이트 완료");
    }

    @ApiOperation(value = "팔로우 요청")
    @PostMapping("/follow")
    public CommonApiResponse<String> followUser(@RequestBody FollowRequestDto followRequestDto) {

        String followerEmail = followRequestDto.getFromEmail();
        String followingEmail = followRequestDto.getToEmail();

        User follower = userService.findByEmail(followerEmail);
        User following = userService.findByEmail(followingEmail);
        followService.following(follower, following);

        return CommonApiResponse.of("팔로우 완료");
    }

    @ApiOperation(value = "언팔로우 요청")
    @PostMapping("/unfollow")
    public CommonApiResponse<String> unfollowUser(@RequestBody FollowRequestDto followRequestDto) {

        String followerEmail = followRequestDto.getFromEmail();
        String followingEmail = followRequestDto.getToEmail();

        System.out.println(followerEmail + " ****" + followingEmail);

        User follower = userService.findByEmail(followerEmail);
        User following = userService.findByEmail(followingEmail);

        Long followId = followService.getFollowIdByFollowerAndFollowing(follower, following);

        followRepository.deleteById(followId);

        return CommonApiResponse.of("언팔로우 완료");
    }


//    @ApiOperation(value = "팔로우 리스트 요청")
//    @PostMapping("/followList")
//    public String followPage(@RequestBody String email, Authentication auth) {
//        User user = userService.findByEmail(email);
//
//        // TODO 프로필 보이도록 코드 추가 해야함
//        UserProfileDto userProfileDto = new UserProfileDto().toUserProfileDto(user);
//
//        // TODO 본인이 프로필 볼 때 + 비공개일때 고려해야 함
//
//        List<User> followerList = followService.getFollower(user);
//        List<User> followingList = followService.getFollowing(user);
//
//        return "followerList = " + followerList + "\nfollowingList = " + followingList;
//    }

//
//    @ApiOperation(value = "회원 탈퇴")
//    @GetMapping("/delete")
//    public String deletePage(Authentication auth) {
//        User user = userService.findByAuth(auth);
//        userService.deleteUser(user);
//
//        return user.getEmail() + " was deleted";
//    }

    @ApiOperation(value = "모든 유저 반환")
    @GetMapping("/search")
    public CommonApiResponse<List<UserProfileDto>> allUserSearch(Authentication auth) {
        User user = userService.findByAuth(auth);
        List<UserProfileDto> allUserList = userService.searchAllUser(user);
        System.out.println(allUserList);
        return CommonApiResponse.of(allUserList);
    }

    @ApiOperation(value = "유저 검색")
    @PostMapping("/search")
    public CommonApiResponse<List<UserProfileDto>> setUserSearch(@RequestBody SearchDto searchDto, Authentication auth) {
        User user = userService.findByAuth(auth);
        String name = searchDto.getName();
        userService.userSearch(user, name);
        List<UserProfileDto> searchUserProfileDtoList = userService.searchUserByName(user, name);
        System.out.println("/*\n" + name + "\n" + searchUserProfileDtoList + "\n*/");
        return CommonApiResponse.of(searchUserProfileDtoList);
    }

    @ApiOperation(value = "리뷰 리스트 반환")
    @GetMapping("/review")
    public CommonApiResponse<List<Review>> getReviewList(Authentication auth) {
        User user = userService.findByAuth(auth);
        System.out.println("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");

        List<Review> reviewList = userService.getReviewListFromUser(user);
        for (Review review : reviewList) {
            System.out.println(review.getImage());
        }

        System.out.println("ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ");
        return CommonApiResponse.of(userService.getReviewListFromUser(user));

    }
//    @GetMapping("/su")
//    public CommonApiResponse<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

