package api;


import lombok.Data;

/**
 * @ClassName ResponseData
 * @Description: 接口请求返回体
 * @Author lirui
 * @Date 2019/12/19
 * @Version V1.0
 **/
@Data
public class ResponseData<T> {

    private T data;

    private String message;

    private String code;

    public ResponseData() {
    }

    public ResponseData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(ExceptionEnumData enums, T data) {
        this.code = enums.getName();
        this.message = enums.getValue();
        this.data = data;
    }

    /**
     * 根据枚举类型返回指定ResponseData
     *
     * @param enums
     * @param data
     * @return
     */
    public static <T> ResponseData<T> buildResponseData(ExceptionEnumData enums, T data) {
        return new ResponseData<>(enums.getName(), enums.getValue(), data);
    }

    /**
     * data 可以为空
     *
     * @param data
     * @return
     */
    public static <T> ResponseData<T> defaultSuccess(T data) {
        return buildResponseData(ExceptionEnumData.SUCCESS, data);
    }

    /**
     * data 可以为空
     *
     * @param data
     * @return
     */
    public static <T> ResponseData<T> defaultFail(T data) {
        return buildResponseData(ExceptionEnumData.FAIL, data);
    }

    /**
     * data 可以为空
     *
     * @param
     * @return
     */
    public static <T> ResponseData<T> defaultSuccess() {
        return buildResponseData(ExceptionEnumData.SUCCESS, null);
    }

    /**
     * data 可以为空
     *
     * @param
     * @return
     */
    public static <T> ResponseData<T> defaultFail() {
        return buildResponseData(ExceptionEnumData.FAIL, null);
    }
}
