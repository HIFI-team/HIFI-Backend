package Backend.HIFI.store;

import Backend.HIFI.review.Review;
import Backend.HIFI.review.ReviewRequestDto;
import Backend.HIFI.review.ReviewService;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final ReviewService reviewService;
    private final UserService userService;

    private final ModelMapper mapper;

    /**가게 등록 view 전달*/
    @GetMapping("/new")
    public String createForm(){
        return "new";
    }

    /**가게 등록*/
    @PostMapping("/new")
    public ResponseEntity<Store> create(@RequestBody StoreRequestDto dto){
        return ResponseEntity.ok(storeService.registration(dto.toEntity()));
    }

    /** 가게 지도 출력 */
    @GetMapping
    public String store(Model model){
        StoreRequestDto dto = mapper.map(storeService.getStores(), StoreRequestDto.class);
        model.addAttribute("stores",dto);
        return "store";
    }

    /** 가게 더보기 */
    @GetMapping("/{id}")
    public String getReview(@PathVariable Long id, Model model, Principal principal){
        Store store = storeService.getStore(id);
        StoreRequestDto storeDto = mapper.map(store, StoreRequestDto.class);
        model.addAttribute("store",storeDto);

        List<Review> reviews = reviewService.findReviewByStore(id);
        List<ReviewRequestDto> reviewDtoList = reviews.stream()
                                                    .map(review -> mapper.map(review, ReviewRequestDto.class))
                                                    .collect(Collectors.toList());
//        List<Review> reviews = store.getReviews();
        model.addAttribute("reviews",reviewDtoList);

        User user = userService.findByEmail(principal.getName());
        /** 빈 리뷰 객체 */
        ReviewRequestDto dto = ReviewRequestDto.builder()
                .user(user)
                .store(store)
                .build();
        model.addAttribute("newReview",dto);

        return "store/show";
    }

}
