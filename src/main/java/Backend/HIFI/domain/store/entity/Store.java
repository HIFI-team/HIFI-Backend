package Backend.HIFI.domain.store.entity;

import Backend.HIFI.global.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stores")
@Getter
@NoArgsConstructor
public class Store extends BaseEntity {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    @Column(columnDefinition = "float default 0.0")
    private float grade;

    private float latitude;

    private float longitude;

    @Builder
    public Store(String address, String name, Category category, String description, float grade, float latitude, float longitude) {
        this.address = address;
        this.name = name;
        this.category = category;
        this.description = description;
        this.grade = grade;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
