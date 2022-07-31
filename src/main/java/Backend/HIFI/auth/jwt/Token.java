package Backend.HIFI.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** req, res 송수신 위한 토큰 클래스입니다
 * @author gengminy (220728) */
public class Token {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Request {
        private String email;
        private String password;
        private String role;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Response {
        private String accessToken;
    }
}
