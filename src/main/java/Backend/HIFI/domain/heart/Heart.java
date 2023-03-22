package Backend.HIFI.domain.heart;

import Backend.HIFI.domain.review.Review;
import Backend.HIFI.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Heart {
    @Id
    @Column(name = "heart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //게시물
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
    //유저
    @JsonIgnoreProperties({"reviewList","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
