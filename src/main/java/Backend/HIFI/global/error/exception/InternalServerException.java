package Backend.HIFI.global.error.exception;

import Backend.HIFI.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class InternalServerException extends BusinessException {
    private String message;

    public InternalServerException(String message) {
        super(ErrorCode._BAD_REQUEST);
        this.message = message;
    }

    public InternalServerException(ErrorCode errorCode, String message) {
        super(errorCode);
        this.message = message;
    }

    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
