package Backend.HIFI.domain.store.dto.request;

import Backend.HIFI.domain.store.entity.CategoryCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PostStoreDto {
    @ApiModelProperty(value = "가게주소", example = "서울특별시 마포구")
    private String address;
    @ApiModelProperty(value = "가게이름", example = "하이디라오")
    private String name;
    @ApiModelProperty(value = "가게종류", example = "음식점")
    //TODO: 변환 필요
    private CategoryCode categoryCode;
    @ApiModelProperty(value = "가게설명", example = "일식집")
    private String description;

}
