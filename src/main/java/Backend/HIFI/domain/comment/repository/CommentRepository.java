package Backend.HIFI.domain.comment.repository;

import Backend.HIFI.domain.comment.entity.Comment;
import Backend.HIFI.domain.review.entity.Review;

import Backend.HIFI.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long> {
    List<Comment> findAllByReview(Review review);
    List<Comment> findAllByUser(User user);
}
