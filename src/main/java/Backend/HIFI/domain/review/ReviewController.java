package Backend.HIFI.domain.review;

import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.request.PutReviewDto;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "stores/{storeId}/reviews")
@RequiredArgsConstructor
@Api(tags = "리뷰")
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 조회
     */
    @GetMapping("/")
    @Operation(summary = "리뷰 조회 요청", description = "리뷰 조회 요청 API 입니다.")
    public ResponseEntity<List<GetReviewDto>> getReviews(@PathVariable("storeId") Long storeId) {
        List<GetReviewDto> reviews = reviewService.getReviews(storeId);
        return ResponseEntity.ok(reviews);
    }


    /**
     * 리뷰 등록
     */
    @PostMapping("/")
    @Operation(summary = "리뷰 등록 요청", description = "리뷰 등록 요청 API 입니다.")
    public ResponseEntity<GetReviewDto> postReview(@PathVariable("storeId") Long storeId, @RequestBody PostReviewDto postReviewDto, @AuthenticationPrincipal String userId) {
        GetReviewDto getReviewDto = reviewService.createReview(storeId, postReviewDto, userId);
        return ResponseEntity.ok(getReviewDto);
    }

    /**
     * 리뷰 수정
     */
    @PostMapping("/{id}")
    @Operation(summary = "리뷰 수정 요청", description = "리뷰 수정 요청 API 입니다.")
    public ResponseEntity<GetReviewDto> updateReview(@PathVariable("storeId") Long storeId,@PathVariable("id") Long id, @RequestBody PutReviewDto putReviewDto, @AuthenticationPrincipal String userId) {
        GetReviewDto getReviewDto = reviewService.updateReview(storeId, id, putReviewDto, userId);
        return ResponseEntity.ok(getReviewDto);
    }


    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "리뷰 삭제 요청", description = "리뷰 삭제 요청 API 입니다.")
    public ResponseEntity deleteReview(@PathVariable("storeId") Long storeId, @PathVariable Long id, @AuthenticationPrincipal String userId) {
        reviewService.deleteReview(storeId, id, userId);
        return ResponseEntity.ok("success");
    }
}
