package Backend.HIFI.domain.comment.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostCommentDto {
    @ApiModelProperty(value = "댓글 내용", example = "여기 맛집 맞아용~!!")
    @NotNull
    private String content;

    public PostCommentDto(String content) {
        this.content = content;
    }
}
