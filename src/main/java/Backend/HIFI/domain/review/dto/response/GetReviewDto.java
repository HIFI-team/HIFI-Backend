package Backend.HIFI.domain.review.dto.response;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.store.dto.StoreMapDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetReviewDto {
    private Long id;
    private String content;
    private UserMapDto user;
    private StoreMapDto store;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int grade;

    public GetReviewDto (Review review) {
        this.id = review.getId();
        this.content = review.getContent();
//        this.user = review.getUser();
//        this.store = review.getStore()
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.grade = review.getGrade();
    }
}
