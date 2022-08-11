package Backend.HIFI.review;

import Backend.HIFI.review.dto.ReviewRequestDto;
import Backend.HIFI.review.dto.ReviewResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping(value = "/review", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper mapper;

    /** 리뷰 등록 */
    @ApiOperation("리뷰 등록 요청")
    @PostMapping("/new")
    public ResponseEntity<ReviewResponseDto> create(@RequestBody ReviewRequestDto dto){
        return ResponseEntity.ok(reviewService.review(dto));
    }

    /**리뷰 삭제*/
    //@DeleteMapping("/delete")

    /**리뷰 클릭 시*/
    @ApiOperation("리뷰 자세히 보기 요청")
    @GetMapping("/{id}")
    public String getComment(@PathVariable Long id, Model model, Principal principal){
        Review review = reviewService.findReview(id);
        ReviewRequestDto dto = mapper.map(review, ReviewRequestDto.class);
        model.addAttribute("review",dto);

        // 코맨트 리스트 및 작성 폼
        return "reviews/"+id;
    }
}
