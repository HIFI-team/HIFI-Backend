package Backend.HIFI.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasicResponse<T> {
    private boolean success;
    private T response;
    private ErrorResponse errorResponse;
}
