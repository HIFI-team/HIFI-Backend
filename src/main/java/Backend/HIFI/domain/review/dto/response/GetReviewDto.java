package Backend.HIFI.domain.review.dto.response;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.review.entity.Review;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetReviewDto {
    @ApiModelProperty(notes = "리뷰 고유번호", example = "1", required = true)
    private Long id;
    @ApiModelProperty(notes = "리뷰 작성자 정보", required = true)
    private UserMapDto user;
    @ApiModelProperty(notes = "리뷰 고유번호", example = "1", required = true)
    private Long storeId;
    @ApiModelProperty(notes = "리뷰 내용", example = "맛있었어요", required = true)
    private String content;
    @ApiModelProperty(notes = "리뷰 사진 주소", example = "")
    private String imgUrl;
    @ApiModelProperty(notes = "리뷰 생성 시간", example = "2023-04-26T08:02:35.401Z", required = true)
    private LocalDateTime createdAt;
    @ApiModelProperty(notes = "리뷰 수정 시간", example = "2023-04-26T08:02:35.401Z", required = true)
    private LocalDateTime updatedAt;
    @ApiModelProperty(notes = "리뷰 등급", example = "5", required = true)
    private int grade;
    @ApiModelProperty(notes = "리뷰 좋아요 개수", example = "0", required = true)
    private int like;

    @Builder
    public GetReviewDto(Long id, UserMapDto user, Long storeId, String content, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, int grade, int like) {
        this.id = id;
//        this.user = user;
        this.storeId = storeId;
        this.content = content;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.grade = grade;
        this.like = like;
    }

    public static GetReviewDto toEntity(Review review) {
        return GetReviewDto.builder()
                .id(review.getId())
//                .user()
                .storeId(review.getStore().getId())
                .content(review.getContent())
                .imgUrl(review.getImgSrc())
                .grade(review.getGrade())
                .like(review.getLikes())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
