package Backend.HIFI.domain.review.service;

import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.review.repository.ReviewRepository;
import Backend.HIFI.domain.store.Store;
import Backend.HIFI.domain.store.StoreService;
import Backend.HIFI.domain.user.User;
import Backend.HIFI.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final StoreService storeService;

    /**
     * 리뷰 등록
     */
    @Override
    @Transactional
    public GetReviewDto postReview(PostReviewDto postReviewDto, String userId) {
        User user = userService.findByEmail(userId);
        Store store = storeService.getStore(postReviewDto.getStoreId());

        /**toEntity*/
        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(postReviewDto.getContent())
                .imgSrc(postReviewDto.getImgSrc())
                .grade(postReviewDto.getGrade())
                .build();

//        store.getReviews().add(review);
//        user.getReviewList().add(review);

        Review saveReview = reviewRepository.save(review);

        return new GetReviewDto(saveReview);
    }

    /**
     * 리뷰 조회
     */
    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));
    }

    //    public List<Review> findReviews(){
////        return reviewRepository.findAllByDelStatus();
//    }
    public List<Review> findReviewByUser(Long userId) {
        User user = userService.findById(userId);
        return reviewRepository.findByUser(user);
    }

    public List<Review> findReviewByStore(Long storeId) {
        Store store = storeService.getStore(storeId);
        return reviewRepository.findByStore(store);
    }

    /**
     * 리뷰 삭제
     */
    @Transactional
    public void deleteReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));
        //연관관계 끊어줌
//        review.getStore().getReviews().remove(review);
//        review.getUser().getReviewList().remove(review);
        review.updateIsDeleted();
    }

    /**
     * 리뷰 신고
     */
//    public ReviewRequestDto updateLikes(Long reviewId, Long userId) {
//        Review review = reviewRepository.findById(reviewId)
//                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));
//        review.increaseLikes();
//        ReviewRequestDto dto= new ReviewRequestDto(review);
//        return dto;
//    }
    public void updateReports(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));
        //review.increaseReports();
    }
}
