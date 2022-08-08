package Backend.HIFI.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews/posts")
    public ResponseEntity<Review> review(@RequestBody ReviewRequestDto dto){
        return ResponseEntity.ok(reviewService.review(dto));
    }
}
