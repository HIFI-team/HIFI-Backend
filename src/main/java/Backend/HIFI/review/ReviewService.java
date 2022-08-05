package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.store.StoreService;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepositoryImpl reviewRepositoryImpl;
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final StoreService storeService;

    /**
     * 리뷰 등록
     * */
    @Transactional
    public void review(Long userId, Long storeId,String content){
        User user = userService.findById(userId);
        Store store= storeService.findOneStore(storeId);

        Review review =Review.builder()
                .user(user).store(store).content(content).build();
        store.getReviews().add(review);
        reviewRepository.save(review);
    }

    /**
     * 리뷰 조회
     * */
    public List<Review> findReview(){
        return reviewRepositoryImpl.findAllByIsBlindFalse();
    }
    public List<Review> findReviewByUser(Long userId){
        User user=userService.findById(userId);
        return reviewRepositoryImpl.findByUser(user);
    }
    public List<Review>findReviewByStore(Long storeId){
        Store store=storeService.findOneStore(storeId);
        return reviewRepositoryImpl.findByStore(store);
    }

    /**
     * 리뷰 삭제
     * */
    @Transactional
    public void deleteReview(Long reviewId){

        Review review = reviewRepository.findById(reviewId)
                        .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 스토어 입니다"));
//        review.getStore().getReviews().remove(review);
        //review.getUser().getReviews().remove(review);
        reviewRepository.delete(review);
    }

    // 리뷰 삭제 -> 스토어의 연관된 리뷰 "직접" 삭제
    // 유저 삭제 -> 리뷰 알아서 삭제됨 (CASCADE)
    // 그러나 리뷰 삭제됐다고 스토어에서는 삭제 안됨

}
