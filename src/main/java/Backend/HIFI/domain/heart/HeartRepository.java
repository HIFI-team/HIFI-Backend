package Backend.HIFI.domain.heart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart,Long> {
    List<Heart> findHeartByReviewId(Long reviewId);
    void deleteByUserIdAndReviewId(Long userId, Long reviewId);
}
