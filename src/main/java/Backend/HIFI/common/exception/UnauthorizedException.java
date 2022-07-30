package Backend.HIFI.common.exception;

import org.springframework.http.HttpStatus;

/** Status: 401
 * Unauthorized Error 반환하는 커스텀 클래스입니다 */
public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public  UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
