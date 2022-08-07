package Backend.HIFI.store;

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
public class Store {
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
    private StoreCategoryCode category_group_code;

    @Column(columnDefinition = "Text default ''")
    private String images;
    @Column(columnDefinition = "Text default ''")
    private String description;

    @Column(columnDefinition = "int default 0")
    private int likes;

    //트리거로 계산
    @Column(columnDefinition = "float default 0.0")
    private float grade;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

}
