package Backend.HIFI.heart;

import Backend.HIFI.review.Review;
import Backend.HIFI.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
