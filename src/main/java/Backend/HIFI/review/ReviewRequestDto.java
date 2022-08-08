package Backend.HIFI.review;

import Backend.HIFI.common.DeleteStatus;
import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String content;
    private User user;
    private Store store;
    private LocalDateTime time;
    private int likes;

    public Review toEntity(){
        return Review.builder()
                .user(user)
                .store(store)
                .content(content)
                .delStatus(DeleteStatus.Y)
                .build();
    }

    /**
     * ModelMapper 찾아보기
     * */
    public ReviewRequestDto fromEntity(Review review){
        return ReviewRequestDto.builder()
                .user(review.getUser())
                .store(review.getStore())
                .content(review.getContent())
                //.time(review.getCreatedAt())
                .build();
    }
}
