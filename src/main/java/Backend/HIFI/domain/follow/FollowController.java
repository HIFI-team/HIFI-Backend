package Backend.HIFI.domain.follow;

import Backend.HIFI.domain.follow.service.FollowService;
import Backend.HIFI.domain.follow.dto.FollowRequestDto;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.repository.ReviewRepository;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import Backend.HIFI.domain.user.service.UserProfileService;
import Backend.HIFI.domain.user.service.UserService;
import Backend.HIFI.global.common.response.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
@Api(tags = "팔로우")
public class FollowController {

    private final FollowService followService;
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final ReviewRepository reviewRepository;


    @PostMapping("/following")
    @Operation(summary = "팔로우 요청", description = "팔로우 요청 API 입니다.")
    public CommonApiResponse<String> followUser(@RequestBody FollowRequestDto followRequestDto) {
        followService.requestFollow(followRequestDto);
        return CommonApiResponse.of("팔로우 완료");
    }

    // TODO @DeleteMapping 변경
    @DeleteMapping("/following")
    @Operation(summary = "언팔로우 요청", description = "언팔로우 요청 API 입니다.")
    public CommonApiResponse<String> unfollowUser(@RequestBody FollowRequestDto followRequestDto) {
        followService.requestUnFollow(followRequestDto);
        return CommonApiResponse.of("언팔로우 완료");
    }

    @PostMapping("/followList")
    @Operation(summary = "팔로우 리스트 요청", description = "유저의 팔로워/팔로잉 리스트 요청 API 입니다.")
    public CommonApiResponse<?> followPage(@RequestBody String email, Authentication auth) {
        User fromUser = userService.findUserByAuth(auth);
        User toUser = userService.findUserByEmail(email);
        UserProfile fromUserProfile = userProfileService.toUserProfile(fromUser);
        UserProfile toUserProfile = userProfileService.toUserProfile(toUser);
        UserProfileDto toUserProfileDto = new UserProfileDto().of(toUserProfile);
//        // TODO 프로필 보이도록 코드 추가 해야함 -> ㅇㄹ

        // TODO 깔끔하게 변경 필요
        List<UserProfile> followerList = followService.getFollower(toUserProfile);
        List<UserProfile> followingList = followService.getFollowing(toUserProfile);
        List<Review> reviewList = new ArrayList<Review>();
        if (userProfileService.canWatchReview(fromUserProfile, toUserProfile))
            reviewList = reviewRepository.findByUser(toUser);
        toUserProfileDto.setFollowerList(followerList);
        toUserProfileDto.setFollowingList(followingList);
        toUserProfileDto.setReviewList(reviewList);

        return CommonApiResponse.of(toUserProfileDto);
    }
}
