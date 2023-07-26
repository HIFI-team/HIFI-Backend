package Backend.HIFI.domain.follow.service;

import Backend.HIFI.domain.follow.dto.FollowRequestDto;
import Backend.HIFI.domain.user.entity.UserProfile;

import java.util.List;

public interface FollowService {
    void following(UserProfile follower, UserProfile following);

    Long getFollowIdByFollowerAndFollowing(UserProfile follower, UserProfile following);

    List<UserProfile> getFollower(UserProfile user);

    List<UserProfile> getFollowing(UserProfile user);

    void requestFollow(FollowRequestDto followRequestDto);

    void requestUnFollow(FollowRequestDto followRequestDto);
}
