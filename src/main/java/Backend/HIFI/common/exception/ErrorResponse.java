package Backend.HIFI.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
//    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(BaseException e) {
        return ResponseEntity
                .status(e.getHttpStatusCode())
                .body(ErrorResponse.builder()
                        .status(e.getHttpStatusCode().value())
                        .error(e.getHttpStatusCode().name())
//                        .code(errorCode.name())
                        .message(e.getMessage())
                        .build()
                );
    }
}