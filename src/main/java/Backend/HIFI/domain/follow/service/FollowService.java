package Backend.HIFI.domain.follow.service;

import Backend.HIFI.domain.follow.entity.Follow;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.follow.repository.FollowRepository;
import Backend.HIFI.domain.user.entity.UserProfile;
import Backend.HIFI.domain.user.repository.UserProfileRepository;
import Backend.HIFI.domain.user.repository.UserRepository;
import Backend.HIFI.domain.follow.dto.FollowRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public void following(UserProfile follower, UserProfile following) {
        Long followerId = follower.getUserId();
        Long followingId = following.getUserId();
        // 팔로우 하고 있는지 아닌지 검증 필요
        followRepository.save(
                Follow.builder()
                        .followerId(followerId)
                        .followingId(followingId)
                        .build()
        );
    }

    public Long getFollowIdByFollowerAndFollowing(UserProfile follower, UserProfile following) {
        // TODO ID로 변경할 지 고려
        Long followerId = follower.getUserId();
        Long followingId = following.getUserId();
        Follow follow = followRepository.findFollowByFollowerIdAndFollowingId(followerId, followingId);
        if (follow != null) return follow.getId();
        return -1L;
    }

//    public List<FollowDto> findFollowerList(UserProfile user) {
//        StringBuffer sb = new StringBuffer();
//
//        sb.append("SELECT u.id, u.email, u.name, u.image,");
//        sb.append("if ((SELECT 1 FROM follow WHERE follower = ? AND following = u.id), TRUE, FALSE) AS followState");
//        sb.append("from user u, follow f");
//        sb.append("WHERE u.id = f.follower AND f.following = ?");
//
//        Query query =
//    }

    // TODO Repository 이용으로 변경 할 것
    public List<UserProfile> getFollower(UserProfile user) {
        Long followingId = user.getUserId();
        List<Follow> followList = followRepository.findFollowByFollowingId(followingId)
                .orElseThrow(() -> new IllegalArgumentException("팔로워가 없습니다."));

        List<UserProfile> followerList = new ArrayList<UserProfile>();

        for (Follow follow : followList) {
            Long followerId = follow.getFollowerId();
            UserProfile follower = userProfileRepository.findById(followerId)
                    .orElseThrow(() -> new IllegalArgumentException("유저 ID가 잘못되었습니다."));
            followerList.add(follower);
        }
        return followerList;
    }

    public List<UserProfile> getFollowing(UserProfile user) {
        Long followerId = user.getUserId();
        List<Follow> followList = followRepository.findFollowByFollowerId(followerId)
                .orElseThrow(() -> new IllegalArgumentException("팔로잉이 없습니다."));
        List<UserProfile> followingList = new ArrayList<>();
        for (Follow follow : followList) {
            Long followingId = follow.getFollowingId();
            UserProfile following = userProfileRepository.findById(followingId)
                    .orElseThrow(() -> new IllegalArgumentException("유저 ID가 잘못되었습니다."));
            followingList.add(following);
        }
        return followingList;
    }

    // TODO
    //  임시, 함수 분해하던지 안쓰던지
    //  0422 필요 없을듯
//    public List<String> getFollowerEmail(User user) {
//        List<Follow> followList = followRepository.findFollowByFollowing(user)
//                .orElseThrow(() -> new IllegalArgumentException("팔로어가 없습니다."));
//        List<String> followerEmailList = new ArrayList<>();
//        for (Follow follow : followList) {
//            followerEmailList.add(follow.getFollower().getEmail());
//        }
//        return followerEmailList;
//    }
//    public List<String> getFollowingEmail(User user) {
//        List<Follow> followList = followRepository.findFollowByFollower(user)
//                .orElseThrow(() -> new IllegalArgumentException("팔로잉이 없습니다."));
//        List<String> followingEmailList = new ArrayList<>();
//        for (Follow follow : followList) {
//            followingEmailList.add(follow.getFollowing().getEmail());
//        }
//        return followingEmailList;
//    }

    public void requestFollow(FollowRequestDto followRequestDto) {
        Long followerId = followRequestDto.getFollwerId();
        Long followingId = followRequestDto.getFollowingId();

        UserProfile follower = userProfileRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));

        UserProfile following = userProfileRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));

        following(follower, following);
    }

    public void requestUnFollow(FollowRequestDto followRequestDto) {
        Long followerId = followRequestDto.getFollwerId();
        Long followingId = followRequestDto.getFollowingId();

        UserProfile follower = userProfileRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));

        UserProfile following = userProfileRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));

        Long followId = getFollowIdByFollowerAndFollowing(follower, following);

        followRepository.deleteById(followId);
    }
}
