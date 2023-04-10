package Backend.HIFI.domain.follow;

import Backend.HIFI.domain.follow.service.FollowService;
import Backend.HIFI.domain.follow.dto.FollowRequestDto;
import Backend.HIFI.global.common.response.CommonApiResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @ApiOperation(value = "팔로우 요청")
    @PostMapping("/following")
    public CommonApiResponse<String> followUser(@RequestBody FollowRequestDto followRequestDto) {
        followService.requestFollow(followRequestDto);
        return CommonApiResponse.of("팔로우 완료");
    }

    @ApiOperation(value = "언팔로우 요청")
    @PostMapping("/unfollowing")
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
}
