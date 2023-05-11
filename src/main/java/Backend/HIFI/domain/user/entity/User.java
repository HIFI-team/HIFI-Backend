package Backend.HIFI.domain.user.entity;

import Backend.HIFI.domain.user.UserRole;
import Backend.HIFI.global.common.entity.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
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

    @Column(unique = true, name="user_email", nullable = false)
    private String email;

    //OAuth 사용시 null 이 들어올 수 있음
    @Column(name="user_password")
    private String password;

    @Column //OAuth Provider
    private String provider;

    @Column(name = "authentication_code", unique = true)
    private String authenticationCode;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.ROLE_USER;

    //권한 부여
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection authorities = new ArrayList<>();
        authorities.add(role);
        return authorities;
    }

    // Auth 처리용. 헷갈리지 말 것
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