package Backend.HIFI.auth.dto;

import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;

public class OAuthRequestDto {
    @Valid
    private String email;

    private String nickname;

    @Valid
    private String provider;

    public User toUser() {
        return User.builder()
                .email(email)
                .role(UserRole.ROLE_USER)
                .provider(provider)
                .nickname(nickname)
                .build();
    }
}
