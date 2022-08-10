package Backend.HIFI.store;

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
    private Long id;
    private String address_name;
    private String place_name;
    private String place_uid;
    private StoreCategoryCode categoryCode;
    private String images;
    private String description;
    private float grade;

    public Store toEntity(){
        return Store.builder()
                .address_name(address_name)
                .place_name(place_name)
                .place_uid(place_uid)
                .categoryCode(categoryCode)
                .build();
    }
}
