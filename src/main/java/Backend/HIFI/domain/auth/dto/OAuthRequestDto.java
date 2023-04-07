package Backend.HIFI.domain.auth.dto;

import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.UserRole;

import javax.validation.Valid;

public class OAuthRequestDto {
    @Valid
    private String email;

    private String name;

    @Valid
    private String provider;

    public User toUser() {
        return User.builder()
                .email(email)
                .role(UserRole.ROLE_USER)
                .provider(provider)
                .name(name)
                .build();
    }
}
