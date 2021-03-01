package customer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicReceiver
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/
@Component
public class TopicReceiver {
    @RabbitListener(queues = "topic.man")
    public void process(String s) {
        System.out.println(s);
    }

    @RabbitListener(queues = "topic.woman")
    public void process2(String s) {
        System.out.println(s);
    }
}
