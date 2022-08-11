package Backend.HIFI.auth;

import Backend.HIFI.auth.dto.TokenRequestDto;
import Backend.HIFI.auth.dto.TokenResponseDto;
import Backend.HIFI.auth.dto.UserRequestDto;
import Backend.HIFI.auth.dto.UserResponseDto;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthService authService;

    /** 로그인 요청 */
    @ApiOperation(value = "로그인 요청")
    @PostMapping(value = "/login")
    public TokenResponseDto postLoginForm(@RequestBody UserRequestDto userRequestDto) {
        final TokenResponseDto tokenResponseDto = authService.login(userRequestDto);
        log.info("api = 로그인 요청, req = {}", userRequestDto);
        return tokenResponseDto;
    }

    /** 회원가입을 요청 */
    @ApiOperation(value = "회원가입 요청")
    @PostMapping(value = "/join")
    public UserResponseDto postJoin(@RequestBody UserRequestDto userRequestDto) {
        final UserResponseDto userResponseDto = authService.join(userRequestDto);
        log.info("api = 회원가입 요청, req = {}", userRequestDto);
        return userResponseDto;
    }

    /** 유저를 로그아웃  시킵니다 */
    @ApiOperation(value = "로그아웃 요청")
    @PostMapping("/logout")
    public String postLogout(HttpServletResponse response) {
        authService.logout(response);
        return "redirect:/";
    }

    /** 테스트용 유저의 권한 변경 */
    @ApiOperation(value = "[테스트용] 유저 권한 변경")
    @PostMapping("/role")
    public User patchUserRole(@RequestParam("userId") String userId) {
        System.out.println("userId = " + userId);
        log.info("api = 어드민 권한 요청, req = {}", userId);        
        return authService.changeRole(Long.parseLong(userId));
    }

    /** 리프레시 토큰 재발급 */
    @ApiOperation(value = "리프레시 토큰을 이용한 엑세스 토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reissue (@RequestBody TokenRequestDto tokenRequestDto){
        log.info("api = 액세스 토큰 재발급 요청, req = {}", tokenRequestDto);
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
