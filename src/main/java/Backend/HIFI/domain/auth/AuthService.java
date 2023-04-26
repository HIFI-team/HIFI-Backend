package Backend.HIFI.domain.auth;

import Backend.HIFI.domain.auth.dto.TokenRequestDto;
import Backend.HIFI.domain.auth.dto.TokenResponseDto;
import Backend.HIFI.domain.auth.dto.UserRequestDto;
import Backend.HIFI.domain.auth.dto.UserResponseDto;
import Backend.HIFI.domain.auth.oauth.kakao.KakaoUserDto;
import Backend.HIFI.domain.user.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface AuthService {
    UserResponseDto join(UserRequestDto userRequestDto);
    TokenResponseDto login(UserRequestDto userRequestDto);
    TokenResponseDto loginKakao(KakaoUserDto kakaoUserDto);

    void logout(HttpServletResponse response);

    User changeRole(Long userId);

    TokenResponseDto reissue(TokenRequestDto tokenRequestDto);
}
