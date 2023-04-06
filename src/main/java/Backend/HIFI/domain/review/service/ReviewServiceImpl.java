package Backend.HIFI.domain.review.service;

import Backend.HIFI.domain.comment.dto.response.GetCommentDto;
import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.request.PutReviewDto;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.review.repository.ReviewRepository;
import Backend.HIFI.domain.store.entity.Store;
import Backend.HIFI.domain.store.service.StoreService;
import Backend.HIFI.domain.user.User;
import Backend.HIFI.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public GetReviewDto createReview(PostReviewDto postReviewDto, String userId) {
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

        Review saveReview = reviewRepository.save(review);

        return new GetReviewDto(saveReview);
    }

    /**
     * 리뷰 조회
     */
    @Override
    public List<GetReviewDto> getReviews(Store store) {
        List<Review> reviews = reviewRepository.findByStore(store);
        List<GetReviewDto> getReviewDtos = new ArrayList<>();

        for (Review review : reviews) {
            GetReviewDto getReviewDto = new GetReviewDto(review);
            getReviewDtos.add(getReviewDto);
        }
        return getReviewDtos;
    }

    /**
     * 댓글 조회
     */
    @Override
    public List<GetCommentDto> getComments(Long id) {
        return null;
    }

    /**
     * 리뷰 수정
     */
    @Override
    @Transactional
    public GetReviewDto updateReview(PutReviewDto putReviewDto, String userId) {
        User user = userService.findByEmail(userId);

        // TODO: Exception 생성 및 던짐
        Review review = reviewRepository.findById(putReviewDto.getReviewId())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));

        // TODO: 작성자 확인 로직 추가

        review.updateReview(putReviewDto.getContent(), putReviewDto.getImgSrc());
        reviewRepository.save(review);
        return new GetReviewDto(review);
    }

    /**
     * 리뷰 삭제
     */
    @Override
    @Transactional
    public void deleteReview(Long reviewId, String userId) {
        User user = userService.findByEmail(userId);

        // TODO: Exception 생성 및 던짐
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));

        // TODO: 작성자 확인 로직 추가
        review.updateIsDeleted();
    }
}
