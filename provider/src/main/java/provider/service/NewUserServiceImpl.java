package provider.service;

import api.ResponseData;
import api.UserServiceApi;
import com.alibaba.dubbo.config.annotation.Service;
import domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lirui
 * @ClassName: 新UserSerVice
 * @Description:
 * @date 2020/6/9 22:20
 */
@Service(interfaceClass = UserServiceApi.class, version = "0.2")
public class NewUserServiceImpl implements UserServiceApi {
    @Override
    public ResponseData getUserList(String userId) {
        User user = new User();
        user.setName("多版本控制");
        ArrayList list = new ArrayList<User>();
        list.add(user);
        return ResponseData.defaultSuccess();
    }
}
