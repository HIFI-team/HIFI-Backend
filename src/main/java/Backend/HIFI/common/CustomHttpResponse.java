package Backend.HIFI.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomHttpResponse<T> {
    String Message;
    T data;

    public static <T> CustomHttpResponse<T> onSuccess(T data) {
        return new CustomHttpResponse<>(null, data);
    }

    public static CustomHttpResponse onFailure(String message) {
        return new CustomHttpResponse(message, null);
    }
}
