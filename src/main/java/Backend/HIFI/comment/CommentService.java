package Backend.HIFI.comment;

import Backend.HIFI.comment.dto.CommentDto;
import Backend.HIFI.review.Review;
import Backend.HIFI.review.ReviewService;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ReviewService reviewService;

    /**코멘트 등록*/
    @Transactional
    public CommentDto comment(CommentDto dto){
        Comment comment=dto.toEntity();
        User user = userService.findById(comment.getUser().getId());
        Review review = reviewService.findReview(comment.getReview().getId());

        //user.getComments().add(comment);
        review.getComments().add(comment);
        commentRepository.save(comment);

        return CommentDto.of(comment);
    }

    //실질적으로 필요한가?
    /**코멘트 조회*/
    public Comment findByReview(Long reviewId){
        return commentRepository.findByReview(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 댓글 입니다"));
    }
    public Comment findByUser(Long userId){
        return commentRepository.findByUser(userId)
                .orElseThrow(()-> new IllegalArgumentException("등록되지 않은 댓글 입니다"));
    }

    /**코멘트 삭제*/
    @Transactional
    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 댓글 입니다"));
        comment.changeDeleteStatus();
    }
}
