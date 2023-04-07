package Backend.HIFI.domain.user.repository;

import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByEmail(String email);

    Optional<UserProfile> findById(Long id);

    boolean existsByEmail(String email);

}
