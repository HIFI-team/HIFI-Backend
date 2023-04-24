package Backend.HIFI.domain.comment.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentDto {
    @ApiModelProperty(value = "댓글 내용", example = "여기 맛집 맞아용~!!")
    private String Content;

    public PostCommentDto(String content) {
        this.Content = content;
    }
}
