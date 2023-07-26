package Backend.HIFI.domain.store;

import Backend.HIFI.domain.store.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * StoreAdminController
 *
 * @author squirMM
 * @date 2023/07/26
 */
@Slf4j
@Controller
@RequestMapping(value = "/stores")
@RequiredArgsConstructor
@Api(tags = "가게_관리자용")
public class StoreAdminController {

    /**
     * 가게 카테고리 조회
     */
    @GetMapping("/categories")
    @Operation(summary = "가게 카테고리 조회 요청", description = "가게 카테고리 조회 요청 API 입니다.")
    public ResponseEntity<Category[]> getCategories(@AuthenticationPrincipal String userId){
        return ResponseEntity.ok(Category.getValues());
    }
}
