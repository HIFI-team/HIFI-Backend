package Backend.HIFI.domain.heart;

import Backend.HIFI.domain.review.ReviewService;
import Backend.HIFI.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = "/review/{review_id}",produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;
    private final UserService userService;
    private final ReviewService reviewService;

//    private final ModelMapper mapper;

    @PostMapping("/heart")
    public ResponseEntity<String> hearts(@PathVariable Long review_id, Authentication authentication){
        heartService.heart(userService.findByAuth(authentication),reviewService.findReview(review_id));
        return ResponseEntity.ok().body("좋아요성공");
    }
    @DeleteMapping("/heart")
    public ResponseEntity<String> unHearts(@PathVariable Long review_id, Authentication authentication){
        heartService.unHeart(userService.findByAuth(authentication).getId(),review_id);
        return ResponseEntity.ok().body("좋아요취소성공");
    }
//    @GetMapping("/heartList")
//    public ResponseEntity<List<HeartDto>> listHeart(@PathVariable Long review_id){
//        List<Heart> hearts = heartService.findHeart(review_id);
//        List<HeartDto> dtos = hearts.stream().map(heart -> mapper.map(heart, HeartDto.class)).collect(Collectors.toList());
//        return ResponseEntity.ok(dtos);
//    }
}
