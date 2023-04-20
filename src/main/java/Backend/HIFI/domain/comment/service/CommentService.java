package Backend.HIFI.domain.comment.service;

import Backend.HIFI.domain.comment.dto.request.PostCommentDto;
import Backend.HIFI.domain.comment.dto.response.GetCommentDto;

import java.util.List;

public interface CommentService {
    GetCommentDto createComment(Long reviewId, PostCommentDto postCommentDto, String userId);

    List<GetCommentDto> getComments(Long reviewId);

    void deleteComment(Long reviewId, Long id, String userId);

}
