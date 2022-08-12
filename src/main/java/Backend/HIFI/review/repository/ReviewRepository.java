package Backend.HIFI.review.repository;

import Backend.HIFI.review.Review;
import Backend.HIFI.review.repository.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository 를 사용할 매소드를 정의합니다.
 * */
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryCustom {
}
