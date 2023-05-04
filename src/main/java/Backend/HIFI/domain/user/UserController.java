package Backend.HIFI.domain.user;

import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.user.dto.SearchDto;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.service.UserProfileService;
import Backend.HIFI.domain.user.service.UserService;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<UserProfileDto> profilePage(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(userProfileService.getMyProfilePage(userId));
    }

    @PostMapping("/profile")
    @Operation(summary = "타인 프로필 조회 요청", description = "타인의 프로필 조회 요청 API 입니다.")
    public ResponseEntity<UserProfileDto> profilePage(@RequestBody String email, @AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(userProfileService.getProfilePage(userId, email));
    }

    @PutMapping("/profile")
    @Operation(summary = "프로필 업데이트 요청", description = "프로필 업데이트 요청 API 입니다.")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDto userProfileDto, @AuthenticationPrincipal String userId) {
        userProfileService.updateProfile(userId, userProfileDto);
        return ResponseEntity.ok("프로필 업데이트 완료");
    }

    @DeleteMapping("/profile")
    @Operation(summary = "유저 탈퇴 요청", description = "유저 탈퇴 요청 API 입니다.")
    public ResponseEntity<?> deletePage(@AuthenticationPrincipal String userId) {
        User user = userService.findByEmail(userId);
        userService.deleteUser(user);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/search")
    @Operation(summary = "모든 유저 조회 요청", description = "검색에 사용되는 모든 유저 조회 요청 API 입니다.")
    public ResponseEntity<List<UserProfileDto>> allUserSearch(@AuthenticationPrincipal String userId) {
        List<UserProfileDto> allUserProfileList = userProfileService.searchAllUserProfile(userId);
        return ResponseEntity.ok(allUserProfileList);
    }

    @PostMapping("/search")
    @Operation(summary = "특정 유저 조회 요청", description = "검색 조건에 부합하는 특정 유저 조회 요청 API 입니다.")
    public ResponseEntity<List<UserProfileDto>> setUserSearch(@RequestBody SearchDto searchDto, @AuthenticationPrincipal String userId) {
        List<UserProfileDto> searchUserProfileDtoList = userProfileService.searchUserByName(userId, searchDto);
        return ResponseEntity.ok(searchUserProfileDtoList);
    }

    @GetMapping("/review")
    @Operation(summary = "본인 리뷰 조회 요청", description = "본인의 리뷰 조회 요청 API 입니다.")
    public ResponseEntity<List<Review>> getMyReviewList(@AuthenticationPrincipal String userId) {
        List<Review> reviewList = userProfileService.getMyReviewList(userId);
        return ResponseEntity.ok(reviewList);
    }

    @PostMapping("/review")
    @Operation(summary = "타인 리뷰 조회 요청", description = "타인의 리뷰 조회 요청 API 입니다.")
    public ResponseEntity<List<Review>> getOtherReviewList(@RequestBody UserProfileDto userProfileDto, @AuthenticationPrincipal String userId) {
        List<Review> reviewList = userProfileService.getOtherReviewList(userId, userProfileDto);
        return ResponseEntity.ok(reviewList);
    }



//    @GetMapping("/su")
//    public ResponseEntity<User> searchUserPage() {

//    }

//    @GetMapping("/ss")
//    public String searchStorePage() { return "ss"; }

}

