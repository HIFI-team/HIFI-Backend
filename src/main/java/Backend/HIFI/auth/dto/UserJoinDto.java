package Backend.HIFI.auth.dto;

import Backend.HIFI.user.User;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
public class UserJoinDto {
    @Email @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    public UserJoinDto(String email, String password, String name) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
