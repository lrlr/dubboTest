package provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName ProviderApp
 * @Description:
 * @Author lirui
 * @Date 2020/3/10
 * @Version V1.0
 **/

@SpringBootApplication
@EnableDubbo
public class ProviderApp {
@Autowired
private RedisTemplate redisTemplate;
    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class, args);
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
       config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);//单redis配置
        //集群状态扫描间隔时间，毫秒
//        config.useClusterServers().setScanInterval(2000)
//                .addNodeAddress("redis://49.235.91.141:6374", "redis://49.235.91.141:6375")
//                .addNodeAddress("redis://49.235.91.141:6376", "redis://49.235.91.141:6377")
//                .addNodeAddress("redis://49.235.91.141:6378", "redis://49.235.91.141:6379");
        return (Redisson) Redisson.create(config);
    }

    @Bean
    public RedisTemplate redisTemplateInit() {
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
