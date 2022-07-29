package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.UserJoinDto;
import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.auth.jwt.Token;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthService authService;

    /** [view] 로그인 양식 띄웁니다 */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /** [view] 로그인 양식을 띄웁니다 json */
    @PostMapping(value = "/login")
    @ResponseBody
    public String postLoginJson(@RequestBody Token.Request request, HttpServletResponse response) {
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        return request.toString();
    }

    /** [view] 로그인 양식을 띄웁니다 form data */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String postLoginForm(Token.Request request, HttpServletResponse response) {
        return authService.login(request.getEmail(), request.getPassword(), response);
    }

    /** [view] 회원가입 양식을 띄웁니다 */
    @GetMapping("/join")
    public String getJoinView(Model model) {
        return "join";
    }

    /** 회원가입 양식을 작성후 보냅니다, Json Body */
    @PostMapping("/join")
    @ResponseBody
    public String postJoinJson(@RequestBody UserJoinDto userJoinDto) {
        authService.join(userJoinDto);
        return "redirect:/";
    }

    /** 회원가입 양식을 post, Form data */
    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postJoinForm(@Valid UserJoinDto userJoinDto) throws HttpClientErrorException.BadRequest {
        authService.join(userJoinDto);
        return "redirect:/";
    }

    /** 유저를 로그아운 시킵니다, 엑세스 토큰 쿠키 삭제 */
    @PostMapping("/logout")
    public String postLogout(HttpServletResponse response) {
        authService.logout(response);
        return "redirect:/";
    }
}
