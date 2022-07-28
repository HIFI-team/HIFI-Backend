package Backend.HIFI.auth;

import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.jwt.Token;
import Backend.HIFI.auth.jwt.UserAuthentication;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtTokenTest {

    @Test
    @DisplayName("JWT 토큰 테스트")
    public void generateTokenTest() {
        final String principal = "gengminy@hifi.com";
        final String credential = "abcd1234@";

        //Token 이 valid 한지 확인
        String token
                = JwtTokenProvider.generateToken(new UserAuthentication(principal, credential));
        assertThat(JwtTokenProvider.validateToken(token)).isTrue();


        //Decoded Token 의 이메일이 일치하는지 확인
        String parsedEmail = JwtTokenProvider.getUserEmailFromJWT(token);
        assertThat(parsedEmail).isEqualTo(principal);
    }
}
