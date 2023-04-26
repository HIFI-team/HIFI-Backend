package Backend.HIFI.domain.comment;

import Backend.HIFI.domain.comment.dto.request.PostCommentDto;
import Backend.HIFI.domain.comment.dto.response.GetCommentDto;
import Backend.HIFI.domain.comment.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "reviews/{reviewId}/comments")
@RequiredArgsConstructor
@Api(tags = "댓글")
public class CommentController {
    private final CommentService commentService;

    /**
     * 코멘트 등록
     */
    @ApiOperation("코멘트 등록 요청")
    @PostMapping("/")
    public ResponseEntity<GetCommentDto> postComment(@PathVariable("reviewId") Long reviewId, @RequestBody PostCommentDto postCommentDto, @AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(commentService.createComment(reviewId, postCommentDto, userId));
    }

    /**
     * 코멘트 조회4
     */
    @ApiOperation("코멘트 조회 요청")
    @GetMapping("/")
    public ResponseEntity<List<GetCommentDto>> getComments(@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.ok(commentService.getComments(reviewId));
    }

    /**
     * 코멘트 삭제
     */
    @ApiOperation("코멘트 삭제 요청")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable("reviewId") Long reviewId, @PathVariable("id") Long id, @AuthenticationPrincipal String userId) {
        commentService.deleteComment(reviewId, id, userId);
        return ResponseEntity.ok("success");
    }
}
