package Backend.HIFI.domain.review.service;

import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.request.PutReviewDto;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;

import java.util.List;

public interface ReviewService {
    GetReviewDto createReview(Long storeId, PostReviewDto postReviewDto, String userId);

    List<GetReviewDto> getReviews(Long storeId);

    GetReviewDto updateReview(Long storeId, Long id, PutReviewDto putReviewDto, String userId);

    void deleteReview(Long storeId, Long id, String userId);
}
