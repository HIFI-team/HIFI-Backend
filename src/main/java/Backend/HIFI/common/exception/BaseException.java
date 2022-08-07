package Backend.HIFI.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/** Http Status 를 담은 에러 응답을 반환하기 위한
 * 예외 처리용 커스텀 클래스입니다 */
@Getter
@NoArgsConstructor
public class BaseException extends RuntimeException {

    HttpStatus httpStatusCode;
    String responseMessage;

    public BaseException(HttpStatus httpStatusCode) {
        super();
        this.httpStatusCode = httpStatusCode;
    }

    public BaseException(HttpStatus httpStatusCode, String responseMessage) {
        super(responseMessage);
        this.httpStatusCode = httpStatusCode;
        this.responseMessage = responseMessage;
    }

    public static BaseException toBaseException(Exception e) {
        return new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
