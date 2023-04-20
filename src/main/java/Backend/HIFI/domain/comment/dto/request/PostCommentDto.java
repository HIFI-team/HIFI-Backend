package Backend.HIFI.domain.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentDto {
    private String Content;

    public PostCommentDto(String content) {
        this.Content = content;
    }
}
