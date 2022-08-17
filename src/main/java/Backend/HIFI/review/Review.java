package Backend.HIFI.review;

import Backend.HIFI.comment.Comment;
import Backend.HIFI.common.entity.BaseTimeEntity;
import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseTimeEntity {
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

    @OneToMany(mappedBy = "review",cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

//    @Column(columnDefinition = "int default 0")
//    private int likes;
//    @Column(columnDefinition = "int default 0")
//    private int reports;


    //==연관관계 매서드==//


    //==비즈니스 로직==//
    /**
     * 리뷰 좋아요 및 신고 업데이트
     * */
//    public void increaseLikes(){
//        this.likes+=1;
//    }
//    public void increaseReports(){
//        this.reports+=1;
//    }


}
