package Backend.HIFI.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "`User`")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue
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
    @Column(name="user_annonymous",nullable = false)
    private Boolean annonymous;

    //    public Follow follow(User user) {
//        Follow following = new Follow();
//        following.setFollower(this);
//        following.setFollowing(user);
//        return following;
//    }


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.annonymous = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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