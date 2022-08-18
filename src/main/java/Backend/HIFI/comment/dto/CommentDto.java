package Backend.HIFI.comment.dto;

import Backend.HIFI.auth.dto.UserMapDto;
import Backend.HIFI.review.dto.ReviewMapDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private UserMapDto user;
    private ReviewMapDto review;
    private String content;
}
