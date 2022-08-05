package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;

import java.util.List;

public interface ReviewRepositoryCustom  {
    List<Review> findByUser(User user);
    List<Review> findByStore(Store store);
    List<Review> findAllByIsBlindFalse();
}
