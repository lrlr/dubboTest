package customer.service;

import api.ResponseData;
import api.UserServiceApi;
import com.alibaba.dubbo.config.annotation.Reference;
import domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LoginServiceImpl
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/10
 * @Version V1.0
 **/
@Service
public class LoginServiceImpl {
    // failfast快速失效，只发起一次调用，失败立即报错
    @Reference(cluster = "failover", retries = 0, interfaceClass = UserServiceApi.class, lazy = true, check = false, timeout = 5000)
    UserServiceApi userServiceApi;

    public String loginService(String userId) {
        ResponseData<List<User>> responseData = userServiceApi.getUserList(userId);
        List<User> userList = responseData.getData();
        if (userList != null && userList.size() > 0) {
            StringBuffer sbf = new StringBuffer();
            sbf.append("success->");
            userList.stream().forEach(str -> sbf.append("name :" + str.getName()));

            return sbf.toString();
        }
        return "fail";
    }
}
