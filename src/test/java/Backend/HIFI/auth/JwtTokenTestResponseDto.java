package Backend.HIFI.auth;

import Backend.HIFI.domain.auth.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtTokenTestResponseDto {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


}
