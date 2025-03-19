package peep.com.todo_backend.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResultDto<T> {
    private HttpStatus statusCode;
    private String resultMsg;
    private String resultDetail;
    private T resultData;

    public ResultDto(final HttpStatus statusCode, final String resultMsg) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultDetail = null;
        this.resultData = null;
    }

    public static <T> ResultDto<T> res(final HttpStatus statusCode, final String resultMsg) {
        return res(statusCode, resultMsg, null, null);
    }

    public static <T> ResultDto<T> res(final HttpStatus statusCode, final String resultMsg, final T t) {
        return res(statusCode, resultMsg, null, t);
    }

    public static <T> ResultDto<T> res(final HttpStatus statusCode, final String resultMsg, final String resultDetail,
            final T t) {
        return ResultDto.<T>builder()
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .resultDetail(resultDetail)
                .resultData(t)
                .build();
    }
}
