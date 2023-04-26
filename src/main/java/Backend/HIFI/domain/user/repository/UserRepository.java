package Backend.HIFI.domain.user.repository;

import Backend.HIFI.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndProvider(String email, String provider);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);

    boolean existsByEmailAndProvider(String email, String provider);

    @Modifying
    @Query("delete from Follow f where f.followerId = :userId or f.followingId = :userId")
    void deleteFollowByUserId(@Param("userId") Long userId);

    // profile 분리로 인해 필요 X
//    @Modifying
//    @Query("delete from Review rv where rv.user.id = :userId")
//    void deleteReviewByUserId(@Param("userId") Long UserId);
//
//
//    @Query("select u from User u where u.name like %:userName%")
//    Optional<List<User>> findUserListByName(@Param("userName") String userName);

}