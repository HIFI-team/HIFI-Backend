package Backend.HIFI.domain.user;

import Backend.HIFI.domain.user.dto.SearchDto;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import Backend.HIFI.domain.user.repository.UserProfileRepository;
import Backend.HIFI.domain.user.service.UserProfileService;
import Backend.HIFI.domain.user.service.UserService;
import Backend.HIFI.global.common.redis.RedisService;
import Backend.HIFI.global.common.response.CommonApiResponse;
import Backend.HIFI.domain.follow.repository.FollowRepository;
import Backend.HIFI.domain.follow.service.FollowService;


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
    private final UserProfileService userProfileService;
    private final FollowService followService;
    private final FollowRepository followRepository;
    private final RedisService redisService;
    private final UserProfileRepository userProfileRepository;

    @ApiOperation(value = "마이프로필 요청")
    @GetMapping("/profile")
    public CommonApiResponse<UserProfileDto> profilePage(Authentication auth) {
        List<UserProfile> list = userProfileRepository.findAll();
        System.out.println("!!!!!!!!!!!!!!!!");
        for (UserProfile userProfile : list) {
            System.out.println(userProfile.getUserId());
        }
        System.out.println("????????????????");
        return CommonApiResponse.of(userProfileService.getMyProfilePage(auth));
    }

    @ApiOperation(value = "프로필 요청")
    @PostMapping("/profile")
    public CommonApiResponse<UserProfileDto> profilePage(@RequestBody String email, Authentication auth) {
        return CommonApiResponse.of(userProfileService.getProfilePage(auth, email));
    }

    @ApiOperation(value = "프로필 업데이트 요청")
    @PostMapping("/update")
    public CommonApiResponse<String> updateProfile(@RequestBody UserProfileDto userProfileDto, Authentication auth) {
        userProfileService.updateProfile(auth, userProfileDto);
        return CommonApiResponse.of("프로필 업데이트 완료");
    }

//
    @ApiOperation(value = "회원 탈퇴")
    @GetMapping("/delete")
    public String deletePage(Authentication auth) {
        User user = userService.findUserByAuth(auth);
        userService.deleteUser(user);

        return user.getEmail() + " was deleted";
    }

    // TODO Follow 분리후 처리 필요
    @ApiOperation(value = "모든 유저 반환")
    @GetMapping("/search")
    public CommonApiResponse<List<UserProfileDto>> allUserSearch(Authentication auth) {
        List<UserProfileDto> allUserProfileList = userProfileService.searchAllUserProfile(auth);
        return CommonApiResponse.of(allUserProfileList);
    }

    // TODO Follow 분리 후 처리 필요
    @ApiOperation(value = "유저 검색")
    @PostMapping("/search")
    public CommonApiResponse<List<UserProfileDto>> setUserSearch(@RequestBody SearchDto searchDto, Authentication auth) {
        List<UserProfileDto> searchUserProfileDtoList = userProfileService.searchUserByName(auth, searchDto);
        return CommonApiResponse.of(searchUserProfileDtoList);
    }

    // TODO review Repository 사용할 것
//    @ApiOperation(value = "리뷰 리스트 반환")
//    @GetMapping("/review")
//    public CommonApiResponse<List<Review>> getReviewList(Authentication auth) {
//        List<Review> reviewList = userProfileService.getReviewListFromUser(auth);
//        return CommonApiResponse.of(reviewList);
//    }

//    @GetMapping("/su")
//    public CommonApiResponse<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

