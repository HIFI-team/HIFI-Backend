package Backend.HIFI.auth;

import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.jwt.Token;
import Backend.HIFI.auth.jwt.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    public String getAccessToken(Token.Request token) {
        UserAuthentication userAuthentication = new UserAuthentication(
                token.getEmail(),
                token.getPassword()
        );
        return JwtTokenProvider.generateToken(userAuthentication);
    }

    public String getDecodedToken(String token) {
        return JwtTokenProvider.getUserEmailFromJWT(token);
    }
}
