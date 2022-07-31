package Backend.HIFI.common.exception;

import org.springframework.http.HttpStatus;

/** Status: 400
 * Bad Request Error 반환하는 커스텀 클래스입니다 */
public class BadRequestException extends BaseException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public  BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
