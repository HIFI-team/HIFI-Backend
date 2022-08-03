package Backend.HIFI.user.follow;

import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public void following(User follower, User following) {

        // 팔로우 하고 있는지 아닌지 검증 필요
        follower.getFollowingList().add(following);
        following.getFollowerList().add(follower);
//        followRepository.save(new Follow(follower, following));
        followRepository.save(
                Follow.builder()
                        .follower(follower)
                        .following(following)
                        .build()
        );
    }

    public void unfollowing(User follower, User following) {

        follower.getFollowingList().remove(following);
        following.getFollowerList().remove(follower);
    }

    public Long getFollowIdByFollowerAndFollowing(User follower, User following) {
        Follow follow = followRepository.findFollowByFollowerAndFollowing(follower, following);

        if (follow != null) return follow.getId();
        return -1L;
    }

}
