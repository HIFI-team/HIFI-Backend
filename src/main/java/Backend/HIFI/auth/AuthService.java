package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.UserJoinDto;
import Backend.HIFI.auth.jwt.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface AuthService {
    /** JWT 토큰을 생성합니다
     * @param token Token.Request dto
     * @return String accessToken */
    String getAccessToken(Token.Request token);

    /** JWT 토큰에서 데이터를 추출합니다
     * @param token 추출할 jwt token
     * @return email - 이메일 주소 */
    String getDecodedToken(String token);

    Long join(UserJoinDto userJoinDto);
    String login(String email, String rawPassword, HttpServletResponse response);

    void logout(HttpServletResponse response);
}
