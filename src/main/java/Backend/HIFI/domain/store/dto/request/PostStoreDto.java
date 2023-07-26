package Backend.HIFI.domain.store.dto.request;

import Backend.HIFI.domain.store.entity.Category;
import Backend.HIFI.global.common.valid.EnumValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostStoreDto {
    @ApiModelProperty(value = "가게 주소", example = "서울특별시 마포구")
    @NotNull
    private String address;
    @ApiModelProperty(value = "가게 이름", example = "하이디라오")
    @NotNull
    private String name;
    @ApiModelProperty(value = "가게 종류", example = "음식점")
    @EnumValid
    private Category category;
    @ApiModelProperty(value = "가게 설명", example = "일식집")
    private String description;
    @ApiModelProperty(value = "가게 위도", example = "35.7")
    private double latitude;
    @ApiModelProperty(value = "가게 경도", example = "138.5")
    private double longitude;
}
