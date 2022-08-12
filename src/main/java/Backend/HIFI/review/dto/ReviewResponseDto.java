package Backend.HIFI.review.dto;

import Backend.HIFI.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private String content;
    private String userName;
    private int grade;
    /**등록시 보여줍니다.*/
    public static ReviewResponseDto of(Review review){
        return new ReviewResponseDto(review.getContent(),review.getUser().getName(), review.getGrade());}
}
