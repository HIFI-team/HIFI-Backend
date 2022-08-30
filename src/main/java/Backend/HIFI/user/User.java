package Backend.HIFI.user;

import Backend.HIFI.common.entity.BaseEntity;
import Backend.HIFI.common.entity.BaseTimeEntity;
import Backend.HIFI.review.Review;
import Backend.HIFI.user.dto.UserProfileDto;
import Backend.HIFI.user.search.Search;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "`User`")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    @Builder.Default
    private String name = "test";

    @Column(name="user_email", nullable = false)
    private String email;

    //OAuth 사용시 null 이 들어올 수 있음
    @Column(name="user_password")
    private String password;

    @Column(name="user_image")
    private String image;

    @Column(name="user_description")
    private String description;

    @Column //OAuth Provider
    private String provider;

    @Column //user nickname
    private String nickname;

    @Column(name = "authentication_code", nullable = false, unique = true)
    private String authenticationCode;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.ROLE_USER;

    @Column(name="user_anonymous",nullable = false)
    @Builder.Default
    private Boolean anonymous = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Search> searchList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();


    public void update(UserProfileDto userProfileDto) {
        this.name = userProfileDto.getName();
        this.description = userProfileDto.getDescription();
        this.anonymous = userProfileDto.getAnonymous();
        this.image = userProfileDto.getImage();
    }

    //권한 부여
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection authorities = new ArrayList<>();
        authorities.add(role);
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static void changeDescription(User user, String description) {
        user.description = description;
    }

    public static void changeImage(User user, String image) {
        user.image = image;
    }

}