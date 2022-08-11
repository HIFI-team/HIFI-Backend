package Backend.HIFI.common.utils;

import Backend.HIFI.common.entity.ErrorResponse;
import Backend.HIFI.common.entity.BasicResponse;

public class ResponseUtil {
    public static <T>BasicResponse<T> onSuccess(T response) {
        return new BasicResponse<>(true, response, null);
    }
    public static BasicResponse<?> onError(ErrorResponse ex) {
        return new BasicResponse<>(false, null, ex);
    }
}
