package Backend.HIFI.domain.comment.dto.response;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.comment.entity.Comment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetCommentDto {
    @ApiModelProperty(notes = "댓글 고유번호", example = "1", required = true)
    private Long id;
    @ApiModelProperty(notes = "댓글 내용", example = "여기 맛집 맞아용~!!", required = true)
    private String content;
    @ApiModelProperty(notes = "댓글 작성자 정보", required = true)
    private UserMapDto user;
    @ApiModelProperty(notes = "댓글 생성 시간", example = "2023-04-26T08:02:35.401Z", required = true)
    private LocalDateTime createdAt;

    @Builder
    public GetCommentDto(Long id, String content, UserMapDto user, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
//        this.user = user;
        this.createdAt = createdAt;
    }

    public static GetCommentDto toEntity(Comment comment) {
        return GetCommentDto.builder()
                .id(comment.getId())
//                .user()
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
