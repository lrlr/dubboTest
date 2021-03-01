package provider.config;


import api.ExceptionEnumData;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    //自定义
    public BusinessException(ExceptionEnumData emCode, String message) {
        this.code = emCode.getName();
        this.message = message;
    }

    //固定
    public BusinessException(ExceptionEnumData emCode) {
        this.code = emCode.getName();
        this.message = emCode.getValue();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BusinessException [code=" + code + ", message=" + message + "]";
    }

}
