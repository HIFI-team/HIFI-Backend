package Backend.HIFI.domain.comment.entity;

import Backend.HIFI.global.common.entity.BaseTimeEntity;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends BaseTimeEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(nullable = true)
    private String content;

    @Column(columnDefinition = "int default 0")
    private int likes;

    @Builder
    public Comment(User user, Review review, String content) {
        this.user = user;
        this.review = review;
        this.content = content;
        this.likes = 0;
    }
}
