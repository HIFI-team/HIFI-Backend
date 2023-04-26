package Backend.HIFI.domain.auth.jwt;

import Backend.HIFI.domain.user.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class JwtDto {

//    private Long id;
    private String name;
    private String email;
    private String authorities;

    public JwtDto(User user) {
//        this.id = user.getId();
        this.email = user.getEmail();
//        this.name = user.getName();
        this.authorities = user.getAuthorities().toString();
    }
}