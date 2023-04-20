package Backend.HIFI.domain.comment.dto.response;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetCommentDto {
    private Long id;
    private String content;
    private UserMapDto user;
    private LocalDateTime createdAt;

    @Builder
    public GetCommentDto(Long id, String content, UserMapDto user, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
//        this.user = user;
        this.createdAt = createdAt;
    }
}
