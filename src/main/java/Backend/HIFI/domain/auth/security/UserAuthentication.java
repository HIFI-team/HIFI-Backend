package Backend.HIFI.domain.auth.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/** Authentication 전용 클래스입니다
 * @author gengminy (220728)
 * */
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    /** 생성자
     * @param principal 유저 아이디
     * @param credentials 유저 비밀번호 */
    public UserAuthentication(String principal, String credentials) {
        super(principal, credentials);
    }

    public UserAuthentication(String principal, String credentials,
                              List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}