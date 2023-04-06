package Backend.HIFI.domain.store.dto;

import Backend.HIFI.domain.store.entity.Store;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {
    private String name;
    private String addressName;
    /**등록 후 해당변수만 보여줍니다.*/
    public static StoreResponseDto of(Store store){
        return new StoreResponseDto(store.getName(),store.getAddress_name());
    }
}
