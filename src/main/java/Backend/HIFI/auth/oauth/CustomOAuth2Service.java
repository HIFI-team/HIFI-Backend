//package Backend.HIFI.auth.oauth;
//
//import Backend.HIFI.user.User;
//import Backend.HIFI.user.UserRepository;
//import Backend.HIFI.user.UserRole;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2Service extends DefaultOAuth2UserService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        //provider 정보 (kakao, google, naver...)
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration()
//                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//        log.info("registrationId = {}", registrationId);
//        log.info("userNameAttributeName = {}", userNameAttributeName);
//
//        //provider 정보 기반 객체 생성
//        OAuth2Attribute oAuth2Attribute =
//                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        final String email = oAuth2Attribute.getEmail();
//        final String name = oAuth2Attribute.getName();
//        final String provider = oAuth2Attribute.getProvider();
//
//        if (userRepository.existsByEmail(email)) {
//            log.info("가입된 회원입니다");
//        } else {
//            User user = User.builder()
//                    .email(email)
//                    .name(name)
//                    .provider(provider)
//                    .role(UserRole.ROLE_USER)
//                    .build();
//
//            userRepository.save(user);
//
//            log.info("회원가입");
//        }
//
//        var memberAttribute = oAuth2Attribute.convertToMap();
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
//                memberAttribute, "email");
//    }
//}
