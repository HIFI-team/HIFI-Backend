package Backend.HIFI.user;

import Backend.HIFI.common.entity.BaseTimeEntity;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private UserRole role;

    @Column(name="user_annonymous",nullable = false)
    private Boolean annonymous;

    @OneToMany
    private List<User> followerList = new ArrayList<>();

    @OneToMany
    private List<User> followingList = new ArrayList<>();


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.annonymous = true;
        this.role = UserRole.valueOf("ROLE_USER");
    }

    //권한 부여
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
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
}