package Backend.HIFI.domain.store.dto.response;

import Backend.HIFI.domain.store.entity.CategoryCode;
import Backend.HIFI.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GetStoreDto {
    private Long id;
    private String address;
    private String name;
    //TODO: 변환 필요
    private CategoryCode categoryCode;
    private String description;
    private float grade;
    private float latitude;
    private float longitude;

    @Builder
    public GetStoreDto(Long id, String address, String name, CategoryCode categoryCode, String description, float grade, float latitude, float longitude) {
        this.id = id;
        this.address = address;
        this.name = name;
        //TODO: 변환 필요
        this.categoryCode = categoryCode;
        this.description = description;
        this.grade = grade;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static GetStoreDto toDto(Store store){
        return GetStoreDto.builder()
                .id(store.getId())
                .address(store.getAddress())
                .name(store.getName())
                .description(store.getDescription())
                .grade(store.getGrade())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .build();
    }
}
