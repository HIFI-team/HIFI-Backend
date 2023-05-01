package Backend.HIFI.domain.follow;

import Backend.HIFI.domain.follow.service.FollowService;
import Backend.HIFI.domain.follow.dto.FollowRequestDto;
import Backend.HIFI.global.common.response.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
@Api(tags = "팔로우")
public class FollowController {

    private final FollowService followService;


    @PostMapping("/following")
    @Operation(summary = "팔로우 요청", description = "팔로우 요청 API 입니다.")
    public CommonApiResponse<String> followUser(@RequestBody FollowRequestDto followRequestDto) {
        followService.requestFollow(followRequestDto);
        return CommonApiResponse.of("팔로우 완료");
    }

    // TODO @DeleteMapping 변경
    @PostMapping("/unfollowing")
    @Operation(summary = "언팔로우 요청", description = "언팔로우 요청 API 입니다.")
    public CommonApiResponse<String> unfollowUser(@RequestBody FollowRequestDto followRequestDto) {
        followService.requestUnFollow(followRequestDto);
        return CommonApiResponse.of("언팔로우 완료");
    }

    @PostMapping("/followList")
    @Operation(summary = "팔로우 리스트 요청", description = "유저의 팔로워/팔로잉 리스트 요청 API 입니다.")
    public String followPage(@RequestBody String email, Authentication auth) {
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
        return null;
    }
}
