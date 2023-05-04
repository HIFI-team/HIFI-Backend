package Backend.HIFI.domain.user;

import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.user.dto.SearchDto;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.service.UserProfileService;
import Backend.HIFI.domain.user.service.UserService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "유저")
public class UserController {

    private final UserService userService;
    private final UserProfileService userProfileService;

    @GetMapping("/profile")
    @Operation(summary = "본인 프로필 조회 요청", description = "본인 프로필 조회 요청 API 입니다.")
    public ResponseEntity<UserProfileDto> profilePage(Authentication auth) {
        return ResponseEntity.ok(userProfileService.getMyProfilePage(auth));
    }

    @PostMapping("/profile")
    @Operation(summary = "타인 프로필 조회 요청", description = "타인의 프로필 조회 요청 API 입니다.")
    public ResponseEntity<UserProfileDto> profilePage(@RequestBody String email, Authentication auth) {
        return ResponseEntity.ok(userProfileService.getProfilePage(auth, email));
    }

    @PutMapping("/profile")
    @Operation(summary = "프로필 업데이트 요청", description = "프로필 업데이트 요청 API 입니다.")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDto userProfileDto, Authentication auth) {
        userProfileService.updateProfile(auth, userProfileDto);
        return ResponseEntity.ok("프로필 업데이트 완료");
    }

    @DeleteMapping("/profile")
    @Operation(summary = "유저 탈퇴 요청", description = "유저 탈퇴 요청 API 입니다.")
    public String deletePage(Authentication auth) {
        User user = userService.findUserByAuth(auth);
        userService.deleteUser(user);

        return user.getEmail() + " was deleted";
    }

    @GetMapping("/search")
    @Operation(summary = "모든 유저 조회 요청", description = "검색에 사용되는 모든 유저 조회 요청 API 입니다.")
    public ResponseEntity<List<UserProfileDto>> allUserSearch(Authentication auth) {
        List<UserProfileDto> allUserProfileList = userProfileService.searchAllUserProfile(auth);
        return ResponseEntity.ok(allUserProfileList);
    }

    @PostMapping("/search")
    @Operation(summary = "특정 유저 조회 요청", description = "검색 조건에 부합하는 특정 유저 조회 요청 API 입니다.")
    public ResponseEntity<List<UserProfileDto>> setUserSearch(@RequestBody SearchDto searchDto, Authentication auth) {
        List<UserProfileDto> searchUserProfileDtoList = userProfileService.searchUserByName(auth, searchDto);
        return ResponseEntity.ok(searchUserProfileDtoList);
    }

    @GetMapping("/review")
    @Operation(summary = "본인 리뷰 조회 요청", description = "본인의 리뷰 조회 요청 API 입니다.")
    public ResponseEntity<List<Review>> getMyReviewList(Authentication auth) {
        List<Review> reviewList = userProfileService.getMyReviewList(auth);
        return ResponseEntity.ok(reviewList);
    }

    @PostMapping("/review")
    @Operation(summary = "타인 리뷰 조회 요청", description = "타인의 리뷰 조회 요청 API 입니다.")
    public ResponseEntity<List<Review>> getOtherReviewList(@RequestBody UserProfileDto userProfileDto, Authentication auth) {
        List<Review> reviewList = userProfileService.getOtherReviewList(auth, userProfileDto);
        return ResponseEntity.ok(reviewList);
    }



//    @GetMapping("/su")
//    public ResponseEntity<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

