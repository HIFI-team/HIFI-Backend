package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter @Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(columnDefinition = "boolean default False")
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

    //==비즈니스 로직==//
    /**
     * 리뷰 삭제
     * */


    /**
     * 리뷰 숨기기
     * */

}
