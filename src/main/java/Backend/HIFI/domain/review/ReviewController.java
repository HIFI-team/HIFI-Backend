package Backend.HIFI.domain.review;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.comment.Comment;
import Backend.HIFI.domain.comment.dto.CommentDto;
import Backend.HIFI.domain.review.dto.ReviewDto;
import Backend.HIFI.domain.review.dto.ReviewMapDto;
import Backend.HIFI.domain.user.User;
import Backend.HIFI.domain.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/review", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
//    private final ModelMapper mapper;

    /** 리뷰 등록 */
    @ApiOperation("리뷰 등록 요청")
    @PostMapping("/new")
    public ResponseEntity<ReviewDto> create(@RequestBody ReviewDto dto){
        return ResponseEntity.ok(reviewService.review(dto));
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

        Review review = reviewService.findReview(id);
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
