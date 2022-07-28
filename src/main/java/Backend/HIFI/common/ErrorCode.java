package Backend.HIFI.common;

import lombok.Getter;
import lombok.Setter;

/** 에러 코드를 관리하기 위한 Enum 입니다
 * @author gengminy (220728) */
@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    // User
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}