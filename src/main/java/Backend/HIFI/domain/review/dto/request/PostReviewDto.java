package Backend.HIFI.domain.review.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReviewDto {
    @ApiModelProperty(value = "스토어 번호", example = "1")
    long storeId;
    @ApiModelProperty(value = "리뷰 내용", example = "맛있었어요")
    String content;
    @ApiModelProperty(value = "이미지 주소", example = "")
    String imgSrc;
    @ApiModelProperty(value = "등급", example = "5")
    int grade;
}
