package Backend.HIFI.domain.store;

import Backend.HIFI.domain.store.dto.request.PostStoreDto;
import Backend.HIFI.domain.store.dto.response.GetStoreDto;
import Backend.HIFI.domain.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Controller
@RequestMapping(value = "/stores")
@RequiredArgsConstructor
@Api(tags = "가게")
public class StoreController {
    private final StoreService storeService;

    /**
     * 가게 등록
     */
    @PostMapping("/")
    @Operation(summary = "가게 등록 요청", description = "가게 등록 요청 API 입니다.")
    public ResponseEntity<GetStoreDto> createStore(@RequestBody @Valid PostStoreDto postStoreDto, @AuthenticationPrincipal String userId) {
        GetStoreDto getStoreDto = storeService.createStore(postStoreDto, userId);
        return ResponseEntity.ok(getStoreDto);
    }

    /**
     * 가게 조회
     */
    @GetMapping("/{id}")
    @Operation(summary = "가게 조회 요청", description = "가게 조회 요청 API 입니다.")
    public ResponseEntity<GetStoreDto> getStore(@PathVariable("id") Long id) {
        GetStoreDto getStoreDto = storeService.getStore(id);
        return ResponseEntity.ok(getStoreDto);
    }

    /**
     * 가게 리스트 조회
     */
    @GetMapping("/")
    @Operation(summary = "가게 리스트 조회 요청", description = "가게 리스트 조회 요청 API 입니다.")
    public ResponseEntity<List<GetStoreDto>> getStores() {
        List<GetStoreDto> getStoreDtos = storeService.getStores();
        return ResponseEntity.ok(getStoreDtos);
    }

    /**
     * 가게 삭제
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "가게 삭제 요청", description = "가게 삭제 요청 API 입니다.")
    public ResponseEntity delete(@PathVariable Long id, @AuthenticationPrincipal String userId) {
        storeService.deleteStore(id, userId);
        return ResponseEntity.ok("success");
    }
}
