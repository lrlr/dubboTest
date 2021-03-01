package customer.controller;

import api.ResponseData;
import api.UserServiceApi;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import customer.service.LoginServiceImpl;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LoginController
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/10
 * @Version V1.0
 **/
@RestController
@RequestMapping
public class LoginController {
    @Reference(check = false, cluster = "failover", version = "0.1", async = false)
    private UserServiceApi userServiceApi;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(User user) throws ExecutionException, InterruptedException {
        ResponseData responseData = userServiceApi.getUserList(user.getName());
//        Future<List<User>> future = RpcContext.getContext().getFuture();
//        List<User> userList = future.get();
        if (!responseData.getCode().equals("0000")) {
            return responseData.getMessage();
        }
        List<User> userList = (List<User>) responseData.getData();
        if (userList != null && userList.size() > 0) {
            StringBuffer sbf;
            sbf = new StringBuffer();
            sbf.append("success->");
            userList.stream().forEach(str -> sbf.append("name :" + str.getName()));
            return sbf.toString();
        }
        return null;
    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() throws ExecutionException, InterruptedException {
        redisTemplate.opsForValue().set("test", "test");
        ReentrantLock lock = new ReentrantLock();
        try {
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
        return null;
    }
}
