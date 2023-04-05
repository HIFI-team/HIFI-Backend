package Backend.HIFI.domain.review;

import Backend.HIFI.domain.comment.Comment;
import Backend.HIFI.domain.review.dto.request.PostReviewDto;
import Backend.HIFI.domain.review.dto.response.GetReviewDto;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.service.ReviewServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/review", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    /** 리뷰 등록 */
    @ApiOperation("리뷰 등록 요청")
    @PostMapping("/new")
    public ResponseEntity<GetReviewDto> postReview(@RequestBody PostReviewDto postReviewDto, @AuthenticationPrincipal String userId){
        GetReviewDto getReviewDto = reviewService.postReview(postReviewDto, userId);
        return ResponseEntity.ok(getReviewDto);
    }

    /**리뷰 삭제*/
    @ApiOperation("리뷰 삭제 요청")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("success");
    }

    /**리뷰 클릭 시*/
    @ApiOperation("리뷰 코멘트 보기 요청")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String ,Object>> getComment(@PathVariable Long id, Authentication authentication){
        Map<String,Object> result= new HashMap<>();

        Review review = reviewService.getReview(id);
//        ReviewDto dto = mapper.map(review, ReviewDto.class);
//        result.put("review",dto);

        List<Comment> comments = review.getComments();
//        List<CommentDto> commentDto = comments.stream()
//                .map(comment -> mapper.map(comment,CommentDto.class))
//                .collect(Collectors.toList());
//        result.put("comment",commentDto);
//
//        if(authentication!=null) {
//            User user = userService.findByAuth(authentication);
//            log.info("api = user 찾기 , req = {}", userService.findByAuth(authentication));
//            UserMapDto userMapDto = mapper.map(user, UserMapDto.class);
//            ReviewMapDto reviewMapDto = mapper.map(review, ReviewMapDto.class);
//            CommentDto buildComment = CommentDto.builder().user(userMapDto).review(reviewMapDto).build();
//            result.put("newComment", buildComment);
//        }

        // 코맨트 리스트 및 작성 폼
        return ResponseEntity.ok(result);
    }
}
