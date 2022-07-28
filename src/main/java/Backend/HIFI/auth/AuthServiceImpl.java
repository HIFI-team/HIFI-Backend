package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.UserJoinDto;
import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.jwt.Token;
import Backend.HIFI.auth.jwt.UserAuthentication;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public String getAccessToken(Token.Request token) {
        UserAuthentication userAuthentication = new UserAuthentication(
                token.getEmail(),
                token.getPassword()
        );
        return JwtTokenProvider.generateToken(userAuthentication);
    }

    @Override
    public String getDecodedToken(String token) {
        return JwtTokenProvider.getUserEmailFromJWT(token);
    }

    @Override
    public String joinUser(UserJoinDto userJoinDto) {
        final String encodedPassword = passwordEncoder.encode(userJoinDto.getPassword());

        final User user = new User(userJoinDto.getEmail(), encodedPassword, userJoinDto.getName());
        System.out.println("user = " + user);
        userService.saveUser(user);
        return "redirect:/";
    }
}
