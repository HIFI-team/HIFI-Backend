package Backend.HIFI.domain.review.repository;

import Backend.HIFI.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository 를 사용할 매소드를 정의합니다.
 * */
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryCustom {
}
