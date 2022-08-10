package Backend.HIFI.store;

import Backend.HIFI.common.DeleteStatus;
import Backend.HIFI.common.entity.BaseEntity;
import Backend.HIFI.review.Review;
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
    private String place_name;
    @Column(unique = true)
    private String place_uid;

    @Enumerated(EnumType.STRING)
    private StoreCategoryCode categoryCode;

    @Column(columnDefinition = "Text default ''")
    private String images;
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
            if(review.getDelStatus()==DeleteStatus.N)review.changeDeleteStatus();
        }
    }

}
