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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address_name;

    @Column(nullable = false)
    private String place_name;
    @Column(unique = true)
    private String place_uid;

    @Enumerated(EnumType.STRING)
    private RestaurantCategoryCode category_group_code;

    private String images;
    private String description;

    @Column(columnDefinition = "int default 0")
    private int likes;

    //트리거로 계산
    @Column(columnDefinition = "float default 0.0")
    private float grade;

    //==생성매소드==//
    public static Restaurant createRestaurant(String address_name, RestaurantCategoryCode category_group_code, String place_name, String place_uid) {
        Restaurant restaurant=new Restaurant();
        restaurant.setAddress_name(address_name);
        restaurant.setPlace_name(place_name);
        restaurant.setPlace_uid(place_uid);
        restaurant.setCategory_group_code(category_group_code);
        return restaurant;
    }

}
