package Backend.HIFI.domain.comment.service;

import Backend.HIFI.domain.comment.dto.request.PostCommentDto;
import Backend.HIFI.domain.comment.dto.response.GetCommentDto;
import Backend.HIFI.domain.comment.entity.Comment;
import Backend.HIFI.domain.comment.repository.CommentRepository;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.repository.ReviewRepository;
import Backend.HIFI.domain.user.User;
import Backend.HIFI.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public GetCommentDto createComment(Long reviewId, PostCommentDto postCommentDto, String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow();
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        Comment comment = Comment.builder()
                .user(user)
                .review(review)
                .content(postCommentDto.getContent())
                .build();

        Comment save = commentRepository.save(comment);

        return GetCommentDto.builder()
                .id(save.getId())
//                .user()
                .content(save.getContent())
                .createdAt(save.getCreatedAt())
                .build();
    }

    @Override
    public List<GetCommentDto> getComments(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        List<Comment> allByReview = commentRepository.findAllByReview(review);

        List<GetCommentDto> getCommentDtos = new ArrayList<>();
        for(Comment comment : allByReview){
            GetCommentDto getCommentDto = GetCommentDto.builder()
                    .id(comment.getId())
//                    .user()
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .build();
            getCommentDtos.add(getCommentDto);
        }

        return getCommentDtos;
    }

    @Override
    @Transactional
    public void deleteComment(Long reviewId, Long id, String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow();
        Comment comment = commentRepository.findById(id).orElseThrow();
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        //TODO: 추후 커스텀 , 옳지않은 접근으로 날려줘야한다.
        if (user != comment.getUser())
            throw new RuntimeException();

        if (review != comment.getReview())
            throw new RuntimeException();

        commentRepository.delete(comment);
    }
}
