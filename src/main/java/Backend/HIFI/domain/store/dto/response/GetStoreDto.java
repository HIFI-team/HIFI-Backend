package Backend.HIFI.domain.store.dto.response;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.store.entity.Category;
import Backend.HIFI.domain.store.entity.Store;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class GetStoreDto {
    @ApiModelProperty(notes = "가게 고유번호", example = "1", required = true)
    private Long id;
    @ApiModelProperty(notes = "가게 주소", example = "서울특별시 마포구", required = true)
    private String address;
    @ApiModelProperty(notes = "가게 이름", example = "하이디라오", required = true)
    private String name;
    @ApiModelProperty(notes = "가게 카테고리", example = "음식점", required = true)
    private String category;
    @ApiModelProperty(notes = "가게 설명", example = "1")
    private String description;
    @ApiModelProperty(notes = "가게 평점", example = "5", required = true)
    private float grade;
    @ApiModelProperty(notes = "가게 위도", example = "138.")
    private double latitude;
    @ApiModelProperty(notes = "가게 경도", example = "133.")
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

    public static GetStoreDto toDto(Store store) {
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
