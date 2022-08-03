package Backend.HIFI.user;

import Backend.HIFI.common.entity.BaseTimeEntity;
import Backend.HIFI.user.search.Search;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String name;

    @Column(name="user_email", nullable = false)
    private String email;

    @Column(name="user_password", nullable = false)
    private String password;

    @Column(name="user_image")
    private String image;

    @Column(name="user_description")
    private String description;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.ROLE_USER;

    @Column(name="user_anonymous",nullable = false)
    @Builder.Default
    private Boolean anonymous = false;

    @OneToMany
    @Builder.Default
    private List<User> followerList = new ArrayList<>();

    @OneToMany
    @Builder.Default
    private List<User> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Search> searchList = new ArrayList<>();


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.anonymous = true;
        this.role = UserRole.valueOf("ROLE_USER");
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