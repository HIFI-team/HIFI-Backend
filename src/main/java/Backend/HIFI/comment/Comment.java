package Backend.HIFI.comment;

import Backend.HIFI.common.entity.BaseTimeEntity;
import Backend.HIFI.review.Review;
import Backend.HIFI.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
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

    //Todo: 추후 Null false 추가
    private String content;
}
