package Backend.HIFI.common.exception;

import org.springframework.http.HttpStatus;

/** Status: 404
 * Not Found Error 반환하는 커스텀 클래스입니다 */
public class NotFoundException extends BaseException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public  NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
