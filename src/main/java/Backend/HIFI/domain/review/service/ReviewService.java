package Backend.HIFI.domain.review.service;

import Backend.HIFI.domain.comment.dto.response.GetCommentDto;
import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.request.PutReviewDto;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.store.Store;

import java.util.List;

public interface ReviewService {
    GetReviewDto createReview(PostReviewDto postReviewDto, String userId);

    List<GetReviewDto> getReviews(Store store);

    List<GetCommentDto> getComments(Long id);

    GetReviewDto updateReview(PutReviewDto putReviewDto, String userId);

    void deleteReview(Long reviewId, String userId);
}
