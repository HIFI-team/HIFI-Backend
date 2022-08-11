package Backend.HIFI.common.entity;

import Backend.HIFI.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
//    private final String code;
    private final String message;

    public ErrorResponse(BaseException e) {
        this.status = e.getHttpStatusCode().value();
        this.error = e.getHttpStatusCode().name();
        this.message = e.getMessage();
    }

//    public static ResponseEntity<ErrorResponse> toResponseEntity(BaseException e) {
//        return ResponseEntity
//                .status(e.getHttpStatusCode())
//                .body(ErrorResponse.builder()
//                        .status(e.getHttpStatusCode().value())
//                        .error(e.getHttpStatusCode().name())
////                        .code(errorCode.name())
//                        .message(e.getMessage())
//                        .build()
//                );
//    }
}