package Backend.HIFI.domain.review;

import Backend.HIFI.domain.comment.dto.response.GetCommentDto;
import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.request.PutReviewDto;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.review.service.ReviewServiceImpl;
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
@RequestMapping(value = "/review")
@RequiredArgsConstructor
@Api(tags = "리뷰")
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    /** 리뷰 등록 */
    @PostMapping("/new")
    @Operation(summary = "리뷰 등록 요청", description = "리뷰 등록 요청 API 입니다.")
    public ResponseEntity<GetReviewDto> postReview(@RequestBody PostReviewDto postReviewDto, @AuthenticationPrincipal String userId){
        GetReviewDto getReviewDto = reviewService.createReview(postReviewDto, userId);
        return ResponseEntity.ok(getReviewDto);
    }

    /** 리뷰 수정 */
    @PostMapping("/update")
    @Operation(summary = "리뷰 수정 요청", description = "리뷰 수정 요청 API 입니다.")
    public ResponseEntity<GetReviewDto> updateReview(@RequestBody PutReviewDto putReviewDto, @AuthenticationPrincipal String userId){
        GetReviewDto getReviewDto = reviewService.updateReview(putReviewDto,userId);
        return ResponseEntity.ok(getReviewDto);
    }


    /**리뷰 삭제*/
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "리뷰 삭제 요청", description = "리뷰 삭제 요청 API 입니다.")
    public ResponseEntity deleteReview(@PathVariable Long id, @AuthenticationPrincipal String userId){
        reviewService.deleteReview(id, userId);
        return ResponseEntity.ok("success");
    }

    /**리뷰 댓글 요청*/
    @GetMapping("/{id}")
    @Operation(summary = "리뷰 댓글 보기 요청", description = "리뷰 댓글 보기 요청 API 입니다.")
    public ResponseEntity<List<GetCommentDto>> getComments(@PathVariable Long id){
        List<GetCommentDto> getCommentDtos = reviewService.getComments(id);

        return ResponseEntity.ok(getCommentDtos);
    }
}
