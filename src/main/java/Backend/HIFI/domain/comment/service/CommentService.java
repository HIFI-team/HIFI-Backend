package Backend.HIFI.domain.comment.service;

import Backend.HIFI.domain.comment.dto.CommentDto;
import Backend.HIFI.domain.comment.entity.Comment;
import Backend.HIFI.domain.comment.repository.CommentRepository;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.service.ReviewServiceImpl;
import Backend.HIFI.domain.user.User;
import Backend.HIFI.domain.user.UserService;
import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ReviewServiceImpl reviewService;

//    private final ModelMapper mapper;

    /**코멘트 등록*/
    @Transactional
    public CommentDto comment(CommentDto dto){
        User user = userService.findByEmail(dto.getUser().getEmail());
//        Review review = reviewService.getReview(dto.getReview().getId());

        Comment comment = null;

        //user.getComments().add(comment);
//        review.getComments().add(comment);
        commentRepository.save(comment);

        return dto;
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
        comment.updateIsDeleted();
    }
}