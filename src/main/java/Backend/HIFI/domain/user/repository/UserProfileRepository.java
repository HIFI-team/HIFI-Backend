package Backend.HIFI.domain.user.repository;

import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    // @email 삭제
//    Optional<UserProfile> findByEmail(String email);


    Optional<UserProfile> findByUserId(Long userId);
//    boolean existsByEmail(String email);

    @Modifying
    @Query("delete from Review rv where rv.user.id = :userId")
    void deleteReviewByUserId(@Param("userId") Long UserId);

    @Query("select u from UserProfile u where u.name like %:userName%")
    Optional<List<UserProfile>> findUserProfileListByName(@Param("userName") String userName);


}
