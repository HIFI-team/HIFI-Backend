package Backend.HIFI.domain.user.repository;

import Backend.HIFI.domain.user.entity.Follow;
import Backend.HIFI.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFollowerAndFollowing(User follower, User following);

    Optional<List<Follow>> findFollowByFollowing(User user);

    Optional<List<Follow>> findFollowByFollower(User user);


}
