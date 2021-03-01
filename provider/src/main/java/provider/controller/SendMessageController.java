package provider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName SendMessageController
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/
@RestController
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
//        HashMap hashMap = new HashMap();
//        hashMap.put("key", "value");
        String msg = "fanoutExchange";
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", msg);
        return "ok";
    }

//    @GetMapping("/sendDirectMessage2")
//    public String sendDirectMessage2() {
////        HashMap hashMap = new HashMap();
////        hashMap.put("key", "value");
//        String msg = "testtopic2";
//        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", msg);
//        return "ok";
//    }
}
