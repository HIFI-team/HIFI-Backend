package Backend.HIFI.domain.review.repository;

import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.store.Store;
import Backend.HIFI.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JpaRepository 를 사용할 매소드를 정의합니다.
 * */
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByUser(User user);
    List<Review> findByStore(Store store);
}
