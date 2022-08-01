package Backend.HIFI.auth;

import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.security.UserAuthentication;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtTokenTestResponseDto {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


}
