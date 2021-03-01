package provider.service;

import api.ExceptionEnumData;
import api.ResponseData;
import api.UserServiceApi;
import com.alibaba.dubbo.config.annotation.Service;
import domain.User;
import org.springframework.transaction.annotation.Transactional;
import provider.config.BusinessException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/10
 * @Version V1.0
 **/
@Service(interfaceClass = UserServiceApi.class, loadbalance = "random", version = "0.1", register = false)
@Transactional
public class UserServiceImpl implements UserServiceApi {
    @Override
    public ResponseData getUserList(String userId) {
        ResponseData.defaultSuccess();
        List<User> list = new ArrayList<>();
        list.add(new User(1, 2, userId));
        if (0 == 0) {
            throw new BusinessException(ExceptionEnumData.DATA_DELETE, "报错了，好鸡儿蛋疼");
        }
        return ResponseData.defaultSuccess(list);

    }
}
