package Backend.HIFI.domain.store;

import Backend.HIFI.global.common.entity.BaseEntity;
import Backend.HIFI.domain.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store extends BaseEntity {
    @Id
    @Column(name="store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address_name;

    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String uid;

    @Enumerated(EnumType.STRING)
    private StoreCategoryCode categoryCode;

    private String img;
    private String description;

    //트리거로 계산
    @Column(columnDefinition = "float default 0.0")
    private float grade;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    //==비즈니스 매서드==//
    public void updateReviews(){
        for (Review review:reviews) {
            review.updateIsDeleted();
        }
    }

}
