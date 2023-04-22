package Backend.HIFI.domain.user.entity;

import Backend.HIFI.domain.user.dto.UserProfileDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;

    @JoinColumn(name = "user_id")
    private Long userId;

    @Column(name="user_profile_name")
    private String name;

    @Column(name="user_profile_image")
    private String image;

    @Column(name="user_profile_description")
    private String description;

    @Column(name="user_profile_anonymous",nullable = false)
    @Builder.Default
    private Boolean anonymous = false;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @Builder.Default
//    private List<Search> searchList = new ArrayList<>();

    public void update(UserProfileDto userProfileDto) {
        this.name = userProfileDto.getName();
        this.description = userProfileDto.getDescription();
        this.anonymous = userProfileDto.getAnonymous();
        this.image = userProfileDto.getImage();
    }

    public static void changeDescription(UserProfile userProfile, String description) {
        userProfile.description = description;
    }

    public static void changeImage(UserProfile userProfile, String image) {
        userProfile.image = image;
    }
}
