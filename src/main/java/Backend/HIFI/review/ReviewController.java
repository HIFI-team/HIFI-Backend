package Backend.HIFI.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews/{storeId}/new")
    public String review(@RequestParam("userId") Long userId,
                               @RequestParam("storeId") Long storeId,
                               @RequestParam("content") String content){

        reviewService.review(userId,storeId,content);
        return "redirect:/";
    }
}
