package Backend.HIFI.domain.review.service;

import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.review.entity.Review;

/**
 * created by squirMM on {date}
 */
public interface ReviewService {
    GetReviewDto postReview(PostReviewDto postReviewDto, String userId);
    Review getReview(Long reviewId);
}
