package Backend.HIFI.store;

import Backend.HIFI.auth.dto.UserMapDto;
import Backend.HIFI.review.Review;
import Backend.HIFI.review.dto.ReviewDto;
import Backend.HIFI.store.dto.StoreMapDto;
import Backend.HIFI.store.dto.StoreRequestDto;
import Backend.HIFI.store.dto.StoreResponseDto;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/store", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;

    private final ModelMapper mapper;

    /**가게 등록*/
    @ApiOperation(value = "가게 등록 요청")
    @PostMapping("/new")
    public ResponseEntity<StoreResponseDto> create(@RequestBody StoreRequestDto dto){
        StoreResponseDto registration = storeService.registration(dto);
        return ResponseEntity.ok(registration);
    }

    /** 가게 지도 출력 */
    @ApiOperation(value = "지도 출력 요청")
    @GetMapping
    public ResponseEntity<List<StoreRequestDto>> store(){
        //TODO: 마커에 필요한 정보만 전송할 것.
        List<Store> storeList = storeService.getStores();
        List<StoreRequestDto> dto = storeList.stream()
                .map(store -> mapper.map(store,StoreRequestDto.class))
                .collect(Collectors.toList());
        log.info("api = 지도 출력 요청 , req = {}", dto);
        return  ResponseEntity.ok(dto);
    }

    /**가게 삭제*/
    @ApiOperation(value = "리뷰 삭제 요청")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        storeService.deleteStore(id);
        return ResponseEntity.ok("success");
    }

    /** 가게 더보기 */
    @ApiOperation(value = "리뷰 출력 요청")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String ,Object>> getReview(@PathVariable Long id, Authentication authentication){
        Map<String,Object> result= new HashMap<>();
        Store store = storeService.getStore(id);
        StoreRequestDto storeDto = mapper.map(store, StoreRequestDto.class);
        result.put("store",storeDto);

        List<Review> reviews = store.getReviews();
        List<ReviewDto> reviewDtoList = reviews.stream()
                                            .map(review -> mapper.map(review, ReviewDto.class))
                                            .collect(Collectors.toList());
        result.put("reviews",reviewDtoList);
        log.info("api = review 호출 요청 , req = {}", reviewDtoList);

        if(authentication!=null) {
            User user = userService.findByAuth(authentication);
            log.info("api = user 찾기 , req = {}", userService.findByAuth(authentication));
            //Todo: 빈 객체 보내는 방식 바꿔야 할지도?
            /** 빈 리뷰 객체 */
            StoreMapDto storeMapDto = mapper.map(store, StoreMapDto.class);
            UserMapDto userMapDto = mapper.map(user, UserMapDto.class);
            ReviewDto dto = ReviewDto.builder().user(userMapDto).store(storeMapDto).build();
            result.put("newReview", dto);
        }
        return  ResponseEntity.ok(result);
    }

}
