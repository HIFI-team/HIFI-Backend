package Backend.HIFI.domain.review.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReviewDto {
    @ApiModelProperty(value = "리뷰 내용", example = "맛있었어요", required = true)
    String content;
    @ApiModelProperty(value = "리뷰 이미지 주소", example = "")
    String imgSrc;
    @ApiModelProperty(value = "리뷰 별점", example = "5", required = true)
    int grade;
}
