package Backend.HIFI.auth.oauth.kakao;

import Backend.HIFI.auth.AuthService;
import Backend.HIFI.auth.dto.TokenResponseDto;
import Backend.HIFI.auth.jwt.JwtTokenProvider;
import Backend.HIFI.common.exception.BadRequestException;
import Backend.HIFI.common.exception.ErrorCode;
import Backend.HIFI.common.exception.InternalServerException;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/auth/kakao")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "카카오 로그인 리다이렉트")
    @GetMapping(value = "/redirect", produces = "application/json; charset=utf-8")
    @ResponseBody
    public CommonApiResponse<String> getKakaoRedirect(
            @RequestParam String code,
            HttpServletResponse response
    ) {
        log.info("카카오 로그인 시도");
        log.info("code = ", code);

        KakaoTokenDto kakaoTokenDto = kakaoService.getKakaoAccessToken(code);

        log.info(kakaoTokenDto.toString());

        KakaoUserDto kakaoUserDto = kakaoService.getKakaoUser(kakaoTokenDto.getAccessToken());

        log.info(kakaoUserDto.toString());

        return CommonApiResponse.of("유저 정보를 성공적으로 불러왔습니다");
    }

    @ApiOperation(value = "카카오 회원가입")
    @PostMapping(value = "/join", produces = "application/json; charset=utf-8")
    @ResponseBody
    public CommonApiResponse<String> postKakaoRegister(
            @RequestBody KakaoRequest kakaoRequest
    ) {
        KakaoUserDto kakaoUserDto =
                kakaoService.getKakaoUser(kakaoRequest.getAccessToken());

        if (kakaoUserDto == null) throw new NotFoundException(ErrorCode.KAKAO_USER_NOT_FOUND);
        if (kakaoUserDto.getKakaoAccount().getEmail() == null) {
            kakaoService.unlink(kakaoRequest.getAccessToken());
            throw new BadRequestException(ErrorCode.KAKAO_USER_EMAIL_NOT_FOUND);
        }

        User kakaoUser = User.builder()
                .email(kakaoUserDto.getKakaoAccount().getEmail())
                .authenticationCode(kakaoUserDto.getAuthenticationCode())
                .nickname(kakaoUserDto.getProperties().getNickname())
                .provider("kakao")
                .role(UserRole.ROLE_USER)
                .build();
        if (kakaoService.join(kakaoUser) == null)
            throw new InternalServerException(ErrorCode._INTERNAL_SERVER_ERROR);

        return CommonApiResponse.of(kakaoUser.getEmail());
    }

    @ApiOperation(value = "카카오 로그인")
    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public CommonApiResponse<TokenResponseDto> postKakaoLogin(
            @RequestBody KakaoRequest kakaoRequest
    ) {
        KakaoUserDto kakaoUserDto =
                kakaoService.getKakaoUser(kakaoRequest.getAccessToken());

        if (kakaoUserDto == null) throw new NotFoundException(ErrorCode.KAKAO_USER_NOT_FOUND);

        User user = userRepository.
    }
}
