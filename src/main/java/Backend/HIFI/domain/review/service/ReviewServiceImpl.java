package Backend.HIFI.domain.review.service;

import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.request.PutReviewDto;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.review.repository.ReviewRepository;
import Backend.HIFI.domain.store.entity.Store;
import Backend.HIFI.domain.store.repository.StoreRepository;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.repository.UserRepository;
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
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    /**
     * 리뷰 등록
     */
    @Override
    @Transactional
    public GetReviewDto createReview(Long storeId, PostReviewDto postReviewDto, String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자 입니다"));
        Store store = storeRepository.findById(storeId)  .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 가게 입니다"));

        /**toEntity*/
        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(postReviewDto.getContent())
                .imgSrc(postReviewDto.getImgSrc())
                .grade(postReviewDto.getGrade())
                .build();

        Review saveReview = reviewRepository.save(review);

        return GetReviewDto.toEntity(saveReview);
    }

    /**
     * 리뷰 조회
     */
    @Override
    public List<GetReviewDto> getReviews(Long storeId) {
        Store store = storeRepository.findById(storeId)  .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 가게 입니다"));
        List<Review> reviews = reviewRepository.findByStore(store);
        List<GetReviewDto> getReviewDtos = new ArrayList<>();

        for (Review review : reviews) {
            GetReviewDto getReviewDto = GetReviewDto.toEntity(review);
            getReviewDtos.add(getReviewDto);
        }
        return getReviewDtos;
    }


    /**
     * 리뷰 수정
     */
    @Override
    @Transactional
    public GetReviewDto updateReview(Long storeId, Long id, PutReviewDto putReviewDto, String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자 입니다"));

        // TODO: Exception 생성 및 던짐
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));

        // TODO: 작성자 확인 로직 추가

        review.updateReview(putReviewDto.getContent(), putReviewDto.getImgSrc());
        Review saveReview = reviewRepository.save(review);

        return GetReviewDto.toEntity(saveReview);
    }

    /**
     * 리뷰 삭제
     */
    @Override
    @Transactional
    public void deleteReview(Long storeId, Long id, String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자 입니다"));

        // TODO: Exception 생성 및 던짐
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 리뷰 입니다"));

        // TODO: 작성자 확인 로직 추가
        review.updateIsDeleted();
    }
}
