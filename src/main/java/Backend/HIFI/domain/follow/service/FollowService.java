package Backend.HIFI.domain.follow.service;

import Backend.HIFI.domain.follow.entity.Follow;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.follow.repository.FollowRepository;
import Backend.HIFI.domain.user.repository.UserRepository;
import Backend.HIFI.domain.user.dto.FollowRequestDto;
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
    private final UserRepository userRepository;

    public void following(User follower, User following) {

        // 팔로우 하고 있는지 아닌지 검증 필요
        followRepository.save(
                Follow.builder()
                        .follower(follower)
                        .following(following)
                        .build()
        );
    }

    public Long getFollowIdByFollowerAndFollowing(User follower, User following) {
        Follow follow = followRepository.findFollowByFollowerAndFollowing(follower, following);

        if (follow != null) return follow.getId();
        return -1L;
    }

//    public List<FollowDto> findFollowerList(User user) {
//        StringBuffer sb = new StringBuffer();
//
//        sb.append("SELECT u.id, u.email, u.name, u.image,");
//        sb.append("if ((SELECT 1 FROM follow WHERE follower = ? AND following = u.id), TRUE, FALSE) AS followState");
//        sb.append("from user u, follow f");
//        sb.append("WHERE u.id = f.follower AND f.following = ?");
//
//        Query query =
//    }

    public List<User> getFollower(User user) {
        List<Follow> followList = followRepository.findFollowByFollowing(user)
                .orElseThrow(() -> new IllegalArgumentException("팔로워가 없습니다."));
        List<User> followerList = new ArrayList<>();
        for (Follow follow : followList) {
            followerList.add(follow.getFollower());
        }
        return followerList;
    }

    public List<User> getFollowing(User user) {
        List<Follow> followList = followRepository.findFollowByFollower(user)
                .orElseThrow(() -> new IllegalArgumentException("팔로잉이 없습니다."));
        List<User> followingList = new ArrayList<>();
        for (Follow follow : followList) {
            followingList.add(follow.getFollowing());
        }
        return followingList;
    }

    // TODO
    //  임시, 함수 분해하던지 안쓰던지
    public List<String> getFollowerEmail(User user) {
        List<Follow> followList = followRepository.findFollowByFollowing(user)
                .orElseThrow(() -> new IllegalArgumentException("팔로어가 없습니다."));
        List<String> followerEmailList = new ArrayList<>();
        for (Follow follow : followList) {
            followerEmailList.add(follow.getFollower().getEmail());
        }
        return followerEmailList;
    }
    public List<String> getFollowingEmail(User user) {
        List<Follow> followList = followRepository.findFollowByFollower(user)
                .orElseThrow(() -> new IllegalArgumentException("팔로잉이 없습니다."));
        List<String> followingEmailList = new ArrayList<>();
        for (Follow follow : followList) {
            followingEmailList.add(follow.getFollowing().getEmail());
        }
        return followingEmailList;
    }

    public void requestFollow(FollowRequestDto followRequestDto) {
        String followerEmail = followRequestDto.getFromEmail();
        String followingEmail = followRequestDto.getToEmail();

//        User follower = userService.findByEmail(followerEmail);
        User follower = userRepository.findByEmail(followerEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));

//        User following = userService.findByEmail(followingEmail);
        User following = userRepository.findByEmail(followingEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));
        following(follower, following);
    }

    public void requestUnFollow(FollowRequestDto followRequestDto) {
        String followerEmail = followRequestDto.getFromEmail();
        String followingEmail = followRequestDto.getToEmail();
//
//        User follower = userService.findByEmail(followerEmail);
        User follower = userRepository.findByEmail(followerEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));
//        User following = userService.findByEmail(followingEmail);

        User following = userRepository.findByEmail(followingEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));

        Long followId = getFollowIdByFollowerAndFollowing(follower, following);

        followRepository.deleteById(followId);
    }
}
