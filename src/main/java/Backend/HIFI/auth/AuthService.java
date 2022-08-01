package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.*;
import Backend.HIFI.user.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface AuthService {
    UserResponseDto join(UserRequestDto userRequestDto);
    TokenResponseDto login(UserRequestDto userRequestDto);

    void logout(HttpServletResponse response);

    User changeRole(Long userId);

    TokenResponseDto reissue(TokenRequestDto tokenRequestDto);
}
