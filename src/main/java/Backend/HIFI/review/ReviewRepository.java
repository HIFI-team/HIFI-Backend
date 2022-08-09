package Backend.HIFI.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JpaRepository 를 사용할 매소드를 정의합니다.
 * */
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>,ReviewRepositoryCustom {
}
