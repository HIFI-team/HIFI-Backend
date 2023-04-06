package Backend.HIFI.domain.comment.repository;

import Backend.HIFI.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long> {
    Optional<Comment> findByReview(Long reviewId);
    Optional<Comment> findByUser(Long userId);
}
