package Backend.HIFI.comment.dto;

import Backend.HIFI.comment.Comment;
import Backend.HIFI.review.Review;
import Backend.HIFI.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private User user;
    private Review review;
    private String content;

    //Todo: mapper 로 변경
    public Comment toEntity(){
        return Comment.builder()
                .user(user)
                .review(review)
                .content(content)
                .build();
    }
    public static CommentDto of(Comment comment){
        return CommentDto.builder()
                .user(comment.getUser())
                .review(comment.getReview())
                .content(comment.getContent())
                .build();
    }
}
