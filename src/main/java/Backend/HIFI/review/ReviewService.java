package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.store.StoreRepository;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    /**
     * 리뷰 등록
     * */
    @Transactional
    public void review(Long userId, Long storeId,String content){
//        User user=userRepository.findById(userId);
//        Store store = storeRepository.findById(storeId);
//
//        Review review = Review.createReview(user,store,content);
//        reviewRepository.save(review);
    }

}
