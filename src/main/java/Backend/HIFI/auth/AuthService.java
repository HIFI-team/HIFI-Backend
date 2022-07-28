package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.UserJoinDto;
import Backend.HIFI.auth.jwt.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String getAccessToken(Token.Request token);
    String getDecodedToken(String token);

    void joinUser(UserJoinDto userJoinDto);
}
