package Backend.HIFI.domain.store.dto.response;

import Backend.HIFI.domain.store.entity.Category;
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
    private String category;
    private String description;
    private float grade;
    private double latitude;
    private double longitude;

    @Builder
    public GetStoreDto(Long id, String address, String name, Category category, String description, float grade, double latitude, double longitude) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.category = category.getViewName();
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
                .category(store.getCategory())
                .description(store.getDescription())
                .grade(store.getGrade())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .build();
    }
}
