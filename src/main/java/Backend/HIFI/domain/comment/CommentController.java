package Backend.HIFI.domain.comment;

import Backend.HIFI.domain.comment.dto.CommentDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = "/comment", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**코멘트 등록*/
    @ApiOperation("코멘트 등록 요청")
    @PostMapping("/new")
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto dto){
        return ResponseEntity.ok(commentService.comment(dto));
    }

    /**코멘트 삭제*/
    @ApiOperation("코멘트 삭제 요청")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok("success");
    }
}
