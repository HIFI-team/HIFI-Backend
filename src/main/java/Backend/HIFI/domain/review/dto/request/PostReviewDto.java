package Backend.HIFI.domain.review.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class PostReviewDto {
    @ApiModelProperty(value = "리뷰 내용", example = "맛있었어요")
    @NotNull
    String content;
    @ApiModelProperty(value = "리뷰 이미지 주소", example = "")
    String imgSrc;
    @ApiModelProperty(value = "리뷰 별점", example = "5")
    @Positive @Max(value = 5)
    int grade;
}
