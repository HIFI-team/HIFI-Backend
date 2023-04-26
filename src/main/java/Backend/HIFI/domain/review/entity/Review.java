package Backend.HIFI.domain.review.entity;

import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.global.common.entity.BaseEntity;
import Backend.HIFI.domain.store.entity.Store;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "reviews")
@NoArgsConstructor
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String content;
    private String imgSrc;

    @Column(columnDefinition = "int default 0")
    private int grade;

    @Column(columnDefinition = "int default 0")
    private int likes;

    @Builder
    public Review(User user, Store store, String content, String imgSrc, int grade) {
        this.user = user;
        this.store = store;
        this.content = content;
        this.imgSrc = imgSrc;
        this.grade = grade;
        this.likes = 0;
    }

    public void updateReview(String content, String imgSrc) {
        this.content = content;
        this.imgSrc = imgSrc;
    }
}
