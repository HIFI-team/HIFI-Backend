package Backend.HIFI.review.repository;

import Backend.HIFI.review.Review;
import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;

import java.util.List;

/**
 * 사용자 정의 매소드를 정의합니다.
 * */
public interface ReviewRepositoryCustom  {
    List<Review> findByUser(User user);
    List<Review> findByStore(Store store);
    List<Review> findAllByDelStatus();
}
