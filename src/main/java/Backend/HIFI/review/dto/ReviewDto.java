package Backend.HIFI.review.dto;

import Backend.HIFI.review.Review;
import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ReviewDto {
    private String content;
    private User user;
    private Store store;
    private LocalDateTime createdAt;
    private int grade;

    public Review toEntity(){
        return Review.builder()
                .user(user)
                .store(store)
                .content(content)
                .build();
    }

    public static ReviewDto of(Review review){
        return new ReviewDto(review.getContent(),review.getUser(),review.getStore(),review.getCreatedAt(),review.getGrade());
    }

}
