package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter @Setter
@Table
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String content;
    private String image;

    @Column(columnDefinition = "int default 0")
    private int grade;
    @Column(columnDefinition = "int default 0")
    private int likes;
    @Column(columnDefinition = "int default 0")
    private int reports;

    private Boolean isBlind;
    private LocalDateTime createdAt;

    //==연관관계 매서드==//
    public void setUser(User user){
        this.user=user;
        //user.getReviews().add(this);
    }
    public void setStore(Store store){
        //스토어 중복 방지
        if (this.store== null) {
            this.store = store;
            store.getReviews().add(this);
        }
    }

    //==생성 매서드==//
    public static Review createReview(Optional<User> user, Store store, String content){
        Review review=new Review();

        //review.setUser(user);
        review.setStore(store);
        review.setContent(content);
        review.setCreatedAt(LocalDateTime.now());

        return review;
    }
    //==비즈니스 로직==//
    /**
     * 리뷰 삭제
     * */
    public void removeReview(){
        //본인이 쓴 리뷰인지 확인 필요
    }

    /**
     * 리뷰 숨기기
     * */

}
