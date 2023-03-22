package Backend.HIFI.domain.comment.dto;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.review.dto.ReviewMapDto;
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
