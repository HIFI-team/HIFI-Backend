package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String content;
    private User user;
    private Store store;
    @CreatedDate
    private LocalDateTime time;

    public Review toEntity(){
        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(content)
                .createdAt(time)
                .build();
        return review;
    }
}
