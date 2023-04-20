package Backend.HIFI.domain.review.dto.response;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetReviewDto {
    private Long id;
    private UserMapDto user;
    private Long storeId;
    private String content;
    private String imgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int grade;
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
}
