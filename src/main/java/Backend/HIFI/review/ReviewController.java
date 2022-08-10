package Backend.HIFI.review;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper mapper;

    /** 리뷰 등록 */
    @PostMapping("/new")
    public ResponseEntity<Review> create(@RequestBody ReviewRequestDto dto){
        return ResponseEntity.ok(reviewService.review(dto.toEntity()));
//        return "redirect:/reviews/" + dto.getStore().getId()
    }

    /**리뷰 삭제*/
    //@DeleteMapping("/delete")

    /**리뷰 클릭 시*/
    @GetMapping("/{id}")
    public String getComment(@PathVariable Long id, Model model, Principal principal){
        Review review = reviewService.findReview(id);
        ReviewRequestDto dto = mapper.map(review, ReviewRequestDto.class);
        model.addAttribute("review",dto);

        // 코맨트 리스트 및 작성 폼
        return "reviews/"+id;
    }
}
