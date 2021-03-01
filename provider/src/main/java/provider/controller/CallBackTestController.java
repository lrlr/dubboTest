package provider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CallBackTestController
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/
@RestController
public class CallBackTestController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    //①消息推送到server，但是在server里找不到交换机
    @GetMapping("/server")
    public String server() {
        String s = "回调测试";
        rabbitTemplate.convertAndSend("ss", "sa", s);
        return "ok";
    }

    //②消息推送到server，找到交换机了，但是没找到队列
    @GetMapping("/server2")
    public String server2() {
        String s = "回调测试";
        rabbitTemplate.convertAndSend("fanout2Exchange", "sa", s);
        return "ok";
    }
//③消息推送到sever，交换机和队列啥都没找到
//④消息推送成功
@GetMapping("/server3")
public String server3() {
    String s = "回调测试";
    rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", s);
    return "ok";
}
}
