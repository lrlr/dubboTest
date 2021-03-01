package provider.controller.RedisController;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLockController
 * @Description: Redis实现分布式锁
 * @Author lirui
 * @Date 2020/5/20
 * @Version V1.0
 **/
@RestController
@RequestMapping("/redis")
public class RedisLockController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Redisson redisson;

    @GetMapping("/lock")
    public String TestRedis() {

        redisTemplate.opsForValue().set("stock", 100 + "");
        String clientId = UUID.randomUUID().toString();
        RLock redisslock = redisson.getLock("stocks");

        try {
//            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("stock", clientId, 10, TimeUnit.SECONDS);
//            if (!aBoolean) {
//                return "error";
//            }
            redisslock.lock(30, TimeUnit.SECONDS);
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                stock = stock - 1;
                redisTemplate.opsForValue().set("stock", stock + "");
                System.out.println("扣减成功，剩余库存" + stock);
            } else {
                System.out.println("扣减失败，剩余库存" + stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisslock.unlock();
//            if (clientId.equals(redisTemplate.opsForValue().get("stock"))) {
//                // 释放锁
//                redisTemplate.delete("stock");
//            }
        }


        return "end";
    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() throws ExecutionException, InterruptedException {
        redisTemplate.opsForValue().set("test", "test");
        return null;
    }
}
