package Backend.HIFI.store.dto;

import Backend.HIFI.store.Store;
import Backend.HIFI.store.StoreCategoryCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
