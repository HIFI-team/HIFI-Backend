package Backend.HIFI.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    @Modifying
    @Query(value = "Insert Into Follow(FOLLOWER, FOLLOWING) Values(:fromUserId, :toUserId)", nativeQuery = true)
    void mFollow(int fromUserId, int toUserId);
}