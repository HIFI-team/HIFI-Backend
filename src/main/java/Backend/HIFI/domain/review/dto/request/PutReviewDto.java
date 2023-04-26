package Backend.HIFI.domain.review.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PutReviewDto {
    @ApiModelProperty(value = "리뷰 유효키", example = "1", required = true)
    Long reviewId;
    @ApiModelProperty(value = "리뷰 내용", example = "맛있었어요", required = true)
    String content;
    @ApiModelProperty(value = "이미지 주소", example = "")
    String imgSrc;
    @ApiModelProperty(value = "등급", example = "5", required = true)
    int grade;
}
