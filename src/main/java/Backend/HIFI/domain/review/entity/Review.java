package Backend.HIFI.domain.review.entity;

import Backend.HIFI.domain.comment.entity.Comment;
import Backend.HIFI.global.common.entity.BaseTimeEntity;
import Backend.HIFI.domain.store.entity.Store;
import Backend.HIFI.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"reviewList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"reviews"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String content;
    private String imgSrc;

    @Column(columnDefinition = "int default 0")
    private int grade;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(columnDefinition = "int default 0")
    private int like;
    @Column(columnDefinition = "int default 0")
    private int disLike;

    @Builder
    public Review(User user, Store store, String content, String imgSrc, int grade, List<Comment> comments, int like, int disLike) {
        this.user = user;
        this.store = store;
        this.content = content;
        this.imgSrc = imgSrc;
        this.grade = grade;
        this.comments = new ArrayList<>();
        this.like = 0;
        this.disLike = 0;
    }

    public void updateReview(String content, String imgSrc) {
        this.content = content;
        this.imgSrc = imgSrc;
    }
}
