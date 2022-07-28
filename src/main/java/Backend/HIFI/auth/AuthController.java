package Backend.HIFI.auth;

import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.jwt.Token;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/register")
    @ResponseBody
    public String register() {
        return "register";
    }

    @PostMapping(value = "/signin", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String postLogin(@RequestBody Token.Request request, HttpServletResponse response) {
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        return request.toString();
    }

    @GetMapping(value = "signup", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getJwt() {
        System.out.println("get jwt");
        Token.Request token
                = new Token.Request("gengminy@hifi.com", "1234abcd@");

        String generatedToken = authService.getAccessToken(token);
        return JwtTokenProvider.getUserEmailFromJWT(generatedToken);
    }

}
