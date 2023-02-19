//package Backend.HIFI.auth.oauth;
//
//import TokenResponseDto;
//import JwtTokenProvider;
//import UserAuthentication;
//import User;
//import UserRepository;
//import UserRole;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private final JwtTokenProvider jwtTokenProvider;
//    private final UserRepository userRepository;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//        //로그인 성공한 사용자
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//
//        final String email = oAuth2User.getName();
//        final String provider = oAuth2User.getAttribute("provider");
//        final String name = oAuth2User.getAttribute("name");
//        final String authorities = oAuth2User.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateOAuth2Token(oAuth2User);
//        log.info("토큰 발행");
//
//        //리디렉션 url 프론트로 지정필요
//        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect")
//                        .queryParam("access", tokenResponseDto.getAccessToken())
//                        .queryParam("refresh", tokenResponseDto.getRefreshToken())
//                        .build().toUriString();
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
//    }
//
////    private String makeRedirectUrl(String token) {
////        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect/"+token)
////                .build().toUriString();
////    }
//}
