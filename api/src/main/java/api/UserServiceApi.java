package api;

import domain.User;

import java.util.List;

/**
 * @ClassName UserServiceApi
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/10
 * @Version V1.0
 **/
public interface UserServiceApi {
    public ResponseData getUserList(String userId);
}
