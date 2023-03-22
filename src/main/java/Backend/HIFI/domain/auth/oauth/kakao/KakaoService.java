package Backend.HIFI.domain.auth.oauth.kakao;

import Backend.HIFI.domain.auth.jwt.JwtTokenProvider;
import Backend.HIFI.global.error.exception.BadRequestException;
import Backend.HIFI.global.error.ErrorCode;
import Backend.HIFI.domain.user.User;
import Backend.HIFI.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
@Slf4j
public class KakaoService {

    private final WebClient webClient;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${KAKAO_BASEURL}")
    private String KAKAO_BASEURL;
    @Value("${KAKAO_RESTAPI_KEY}")
    private String KAKAO_RESTPAPI_KEY;
    @Value("${KAKAO_REDIRECT_URI}")
    private String KAKAO_REDIRECT_URL;

    public KakaoTokenDto getKakaoAccessToken(String code) {
        String getTokenURL =
                "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id="
                        + KAKAO_RESTPAPI_KEY + "&redirect_uri=" + KAKAO_BASEURL + KAKAO_REDIRECT_URL + "&code="
                        + code;

        try {
            KakaoTokenDto kakaoTokenDto =
                    webClient.post()
                            .uri(getTokenURL)
                            .retrieve()
                            .bodyToMono(KakaoTokenDto.class).block();
            return kakaoTokenDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(ErrorCode.KAKAO_BAD_REQUEST);
        }
    }

    public KakaoUserDto getKakaoUser(String kakaoAccessToken) {
        String getUserURL = "https://kapi.kakao.com/v2/user/me";

        try {
            KakaoUserDto kakaoUserDto =
                    webClient.post()
                            .uri(getUserURL)
                            .header("Authorization", "Bearer " + kakaoAccessToken)
                            .retrieve()
                            .bodyToMono(KakaoUserDto.class)
                            .block();

            System.out.println("kakaoUserDto = " + kakaoUserDto);

            return kakaoUserDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(ErrorCode.KAKAO_BAD_REQUEST);
        }
    }

    public User join(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new BadRequestException(ErrorCode.USER_ALREADY_EXIST);
        return userRepository.save(user);
    }

//    public TokenResponseDto login(KakaoRequest kakaoRequest) {
//        KakaoUserDto kakaoUserDto =
//                getKakaoUser(kakaoRequest.getAccessToken());
//
//        if (kakaoUserDto == null) throw new NotFoundException(ErrorCode.KAKAO_USER_NOT_FOUND);
//
//        User user = userRepository.findByEmailAndProvider(
//                kakaoUserDto.getKakaoAccount().getEmail(), "kakao")
//                .orElseThrow(() -> new NotFoundException(ErrorCode.KAKAO_USER_NOT_FOUND));
//
//        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateOAuth2Token(user);
//
//        //Refresh Token 저장
//        return tokenResponseDto;
//    }

    public void unlink(String kakaoAccessToken) {
        String unlinkUrl = "https://kapi.kakao.com/v1/user/unlink";

        try {
            String response =
                    webClient.post()
                            .uri(unlinkUrl)
                            .header("Authorization", "Bearer " + kakaoAccessToken)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();

            log.info(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(ErrorCode.KAKAO_USER_NOT_FOUND);
        }
    }
}