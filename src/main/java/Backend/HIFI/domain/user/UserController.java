package Backend.HIFI.domain.user;

import Backend.HIFI.domain.user.dto.FollowRequestDto;
import Backend.HIFI.global.common.redis.RedisService;
import Backend.HIFI.global.common.response.CommonApiResponse;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.follow.FollowRepository;
import Backend.HIFI.domain.user.search.SearchDto;
import Backend.HIFI.domain.user.dto.UserDto;
import Backend.HIFI.domain.user.follow.FollowService;
import Backend.HIFI.domain.review.entity.Review;


import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
        return CommonApiResponse.of(userService.getMyProfilePage(auth));
    }

    @ApiOperation(value = "프로필 요청")
    @PostMapping("/profile")
    public CommonApiResponse<UserDto> profilePage(@RequestBody String email, Authentication auth) {
        return CommonApiResponse.of(userService.getProfilePage(auth, email));
    }

    @ApiOperation(value = "프로필 업데이트 요청")
    @PostMapping("/update")
    public CommonApiResponse<String> updateProfile(@RequestBody UserProfileDto userProfileDto) {
        userService.updateProfile(userProfileDto);
        return CommonApiResponse.of("프로필 업데이트 완료");
    }

    @ApiOperation(value = "팔로우 요청")
    @PostMapping("/follow")
    public CommonApiResponse<String> followUser(@RequestBody FollowRequestDto followRequestDto) {
        followService.requestFollow(followRequestDto);
        return CommonApiResponse.of("팔로우 완료");
    }

    @ApiOperation(value = "언팔로우 요청")
    @PostMapping("/unfollow")
    public CommonApiResponse<String> unfollowUser(@RequestBody FollowRequestDto followRequestDto) {
        followService.requestUnFollow(followRequestDto);
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
        List<UserProfileDto> allUserList = userService.searchAllUser(auth);
        return CommonApiResponse.of(allUserList);
    }

    @ApiOperation(value = "유저 검색")
    @PostMapping("/search")
    public CommonApiResponse<List<UserProfileDto>> setUserSearch(@RequestBody SearchDto searchDto, Authentication auth) {
        List<UserProfileDto> searchUserProfileDtoList = userService.searchUserByName(auth, searchDto);
        return CommonApiResponse.of(searchUserProfileDtoList);
    }

    @ApiOperation(value = "리뷰 리스트 반환")
    @GetMapping("/review")
    public CommonApiResponse<List<Review>> getReviewList(Authentication auth) {
        List<Review> reviewList = userService.getReviewListFromUser(auth);
        return CommonApiResponse.of(reviewList);

    }
//    @GetMapping("/su")
//    public CommonApiResponse<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

