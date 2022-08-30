package Backend.HIFI.auth.oauth.kakao;

import Backend.HIFI.auth.AuthService;
import Backend.HIFI.auth.dto.TokenResponseDto;
import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.common.exception.BadRequestException;
import Backend.HIFI.common.exception.ErrorCode;
import Backend.HIFI.common.exception.NotFoundException;
import Backend.HIFI.common.response.CommonApiResponse;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import Backend.HIFI.user.UserRole;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/auth/kakao")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "카카오 로그인")
    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public CommonApiResponse<String> postKakaoLogin(@RequestBody KakaoRequest kakaoRequest) {
        HttpServletResponse response;


    }

    @ApiOperation(value = "카카오 회원가입")
    @PostMapping(value = "/join", produces = "application/json; charset=utf-8")
    @ResponseBody
    public CommonApiResponse<TokenResponseDto> postKakaoRegister(@RequestBody KakaoTokenDto kakaoTokenDto) {
        HttpServletResponse response;

        KakaoUserDto kakaoUserDto =
                kakaoService.getKakaoUser(kakaoTokenDto);

        if (kakaoUserDto == null) throw new NotFoundException(ErrorCode.KAKAO_USER_NOT_FOUND);
        if (kakaoUserDto.getKakaoAccount().getEmail() == null) {
//            kakaoService.unlinkUser(kakaoTokenDto);
            throw new BadRequestException(ErrorCode.KAKAO_USER_EMAIL_NOT_FOUND);
        }

        User kakaoUser = User.builder()
                .email(kakaoUserDto.getKakaoAccount().getEmail())
                .authenticationCode(kakaoUserDto.getAuthenticationCode())
                .provider("kakao")
                .role(UserRole.ROLE_USER)
                .build();
        userRepository.save(kakaoUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                kakaoUser.getEmail(), "", kakaoUser.getAuthorities()
        )
        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateToken(authentication);
        return CommonApiResponse.of(tokenResponseDto);
    }
}
