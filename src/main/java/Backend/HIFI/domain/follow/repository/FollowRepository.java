package Backend.HIFI.domain.follow.repository;

import Backend.HIFI.domain.follow.entity.Follow;
import Backend.HIFI.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

//    Follow findFollowByFollowerAndFollowing(UserProfile follower, UserProfile following);
    Follow findFollowByFollowerIdAndFollowingId(Long followerId, Long followingId);

    Optional<List<Follow>> findFollowByFollowingId(Long followingId);

    Optional<List<Follow>> findFollowByFollowerId(Long followerId);


}
