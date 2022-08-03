package Backend.HIFI.user.follow;

import Backend.HIFI.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFollowerAndFollowing(User follower, User following);

}
