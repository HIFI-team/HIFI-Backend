package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.TokenRequestDto;
import Backend.HIFI.auth.dto.TokenResponseDto;
import Backend.HIFI.auth.dto.UserRequestDto;
import Backend.HIFI.auth.dto.UserResponseDto;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
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


    /** 로그인 요청 */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<TokenResponseDto> postLoginForm(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.login(userRequestDto));
    }

    /** [view] 회원가입 양식을 띄웁니다 */
    @GetMapping("/join")
    public String getJoinView(Model model) {
        return "join";
    }

    /** 회원가입을 요청 */
    @PostMapping(value = "/join")
    public ResponseEntity<UserResponseDto> postJoin(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.join(userRequestDto));
    }

    /** 유저를 로그아운 시킵니다, 엑세스 토큰 쿠키 삭제 */
    @PostMapping("/logout")
    public String postLogout(HttpServletResponse response) {
        authService.logout(response);
        return "redirect:/";
    }

    /** 테스트용 유저의 권한 변경 */
    @PostMapping("/role")
    @ResponseBody
    public User patchUserRole(@RequestParam("userId") String userId) {
        System.out.println("userId = " + userId);
        return authService.changeRole(Long.parseLong(userId));
    }

    /** 리프레시 토큰 재발급 */
    @GetMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reissue (@RequestBody TokenRequestDto tokenRequestDto){
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
