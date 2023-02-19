package Backend.HIFI.domain.review.repository;

import Backend.HIFI.domain.review.Review;
import Backend.HIFI.domain.store.Store;
import Backend.HIFI.domain.user.User;

import java.util.List;

/**
 * 사용자 정의 매소드를 정의합니다.
 * */
public interface ReviewRepositoryCustom  {
    List<Review> findByUser(User user);
    List<Review> findByStore(Store store);
    List<Review> findAllByDelStatus();
}
