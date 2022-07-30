package Backend.HIFI.common.exception;

import org.springframework.http.HttpStatus;

/** Status: 500
 * Internal Server Error 반환하는 커스텀 클래스입니다 */
public class InternalServerException extends BaseException {
    public InternalServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public  InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
