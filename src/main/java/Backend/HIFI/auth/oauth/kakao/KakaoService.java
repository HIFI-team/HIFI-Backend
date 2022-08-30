package Backend.HIFI.auth.oauth.kakao;

import Backend.HIFI.auth.dto.TokenResponseDto;
import Backend.HIFI.common.exception.BadRequestException;
import Backend.HIFI.common.exception.ErrorCode;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoService {

    private final WebClient webClient;
    private final UserService userService;

    @Value("${KAKAO_RESTAPI_KEY}")
    private String KAKAO_RESTPAPI_KEY;
    @Value("${KAKAO_REDIRECT_URI}")
    private String KAKAO_REDIRECT_URL;

    public KakaoTokenDto getKakaoAccessToken(KakaoRequest kakaoRequest) {
        String getTokenURL =
                "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id="
                        + KAKAO_RESTPAPI_KEY + "&redirect_uri=" + KAKAO_REDIRECT_URL + "&code="
                        + kakaoRequest.getCode();

        WebClient.ResponseSpec responseSpec = webClient.post().uri(getTokenURL).retrieve();

        try {
            KakaoTokenDto kakaoTokenDto = responseSpec.bodyToMono(KakaoTokenDto.class).block();
            return kakaoTokenDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(ErrorCode.KAKAO_BAD_REQUEST);
        }
    }

    public KakaoUserDto getKakaoUser(KakaoTokenDto kakaoTokenDto) {
        String getUserURL = "https://kapi.kakao.com/v2/user/me";

        WebClient.ResponseSpec responseSpec = webClient.post().uri(getUserURL)
                .header("Authorization", "Bearer " + kakaoTokenDto.getAccessToken()).retrieve();

        try {
            KakaoUserDto kakaoUserDto = responseSpec.bodyToMono(KakaoUserDto.class).block();
            return kakaoUserDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(ErrorCode.KAKAO_BAD_REQUEST);
        }
    }
//
//    public TokenResponseDto loginWithAuthenticationCode(KakaoUserDto kakaoUserDTO,
//                                                        HttpServletResponse response) {
//        User user = user.findByAuthenticationCode(kakaoUserDTO.getAuthenticationCode());
//        if (user.isPresent()) {
//            LoginDTO loginDTO = userService.issueNewTokens(user.get(), response);
//
//            return loginDTO;
//        } else {
//            User newUser = User.builder()
//                    .username(kakaoUserDTO.getKakaoAccount().getProfile().getNickname())
//                    .authenticationCode(kakaoUserDTO.getAuthenticationCode())
//                    .provider("kakao").refreshToken("")
//                    .build();
//            uRepo.save(newUser);
//
//            LoginDTO loginDTO = userService.issueNewTokens(newUser, response);
//
//            return loginDTO;
//        }
//    }
}
