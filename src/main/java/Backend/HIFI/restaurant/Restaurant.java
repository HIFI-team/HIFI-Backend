package Backend.HIFI.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class Restaurant {
    @Id
    @Column(name="restaurant_id")
    @GeneratedValue
    private Long id;

    private String name;
    private String images;
    private String description;

    private int likes;
    private float grade;

    private Long uid;

}
