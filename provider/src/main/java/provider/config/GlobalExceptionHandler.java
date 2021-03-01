package provider.config;

import api.ExceptionEnumData;
import api.ResponseData;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName GlobalExceptionHandler
 * @Description: 全局异常处理
 * @Author lirui
 * @Date 2019/12/19
 * @Version V1.0
 **/
@ControllerAdvice
@ResponseBody

public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData<?> exceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bingingResult = e.getBindingResult();
        return new ResponseData<Void>(ExceptionEnumData.FAIL.getName(), bingingResult.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseData<?> exceptionHandler(HttpServletRequest request, Exception e) {

        //请求接口地址
        String url = request.getRequestURL().toString();
        ResponseData<?> response = new ResponseData<>();
        response.setCode(ExceptionEnumData.FAIL.getName());
        response.setMessage(ExceptionEnumData.FAIL.getValue());
        //绑定异常是需要明确提示给用户的
        if (e instanceof BusinessException) {
            response.setCode(((BusinessException) e).getCode());
            response.setMessage(((BusinessException) e).getMessage());
            log.error("访问接口业务异常=" + url + "--->入参:" + JSON.toJSONString(request.getParameterMap()));
        } else {
            log.error("访问接口程序异常=" + url + "--->入参:" + JSON.toJSONString(request.getParameterMap()), e);
        }
        return response;
    }


}