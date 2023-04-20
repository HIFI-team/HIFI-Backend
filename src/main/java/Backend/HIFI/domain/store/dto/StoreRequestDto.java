package Backend.HIFI.domain.store.dto;

import Backend.HIFI.domain.store.entity.Store;
import Backend.HIFI.domain.store.entity.StoreCategoryCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequestDto {
    private String address_name;
    private String name;
    private String uid;
    private StoreCategoryCode categoryCode;
    private String images;
    private String description;
    private float grade;

    public Store toEntity(){
        return Store.builder()
                .address_name(address_name)
                .name(name)
                .uid(uid)
                .categoryCode(categoryCode)
                .build();
    }
}
