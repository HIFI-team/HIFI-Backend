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
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final StoreService storeService;

    /**
     * 리뷰 등록
     * */
    @Transactional
    public Review review(ReviewRequestDto dto){
        User user = userService.findById(dto.getUser().getId());
        Store store= storeService.findOneStore(dto.getStore().getId());

        Review review=dto.toEntity();

        store.getReviews().add(review);
        user.getReviewList().add(review);

        reviewRepository.save(review);
        return review;
    }

    /**
     * 리뷰 조회
     * */
    public List<Review> findReview(){
        return reviewRepository.findAllByIsBlindFalse();
    }
    public List<Review> findReviewByUser(Long userId){
        User user=userService.findById(userId);
        return reviewRepository.findByUser(user);
    }
    public List<Review>findReviewByStore(Long storeId){
        Store store=storeService.findOneStore(storeId);
        return reviewRepository.findByStore(store);
    }

    /**
     * 리뷰 삭제
     * */
    @Transactional
    public void deleteReview(Long reviewId){

        Review review = reviewRepository.findById(reviewId)
                        .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));

        //연관관계 끊어줌
        review.getStore().getReviews().remove(review);
        review.getUser().getReviewList().remove(review);

        reviewRepository.delete(review);
    }

    /**
     * 리뷰 신고
     * */
//    public ReviewRequestDto updateLikes(Long reviewId, Long userId) {
//        Review review = reviewRepository.findById(reviewId)
//                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));
//        review.increaseLikes();
//        ReviewRequestDto dto= new ReviewRequestDto(review);
//        return dto;
//    }

    public void updateReports(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));
        review.increaseReports();
    }
}
