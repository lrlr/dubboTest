package customer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicReceiver
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/
@Component
public class FanoutReceiver {
    @RabbitListener(queues = "queue1")
    public void process(String s) {
        System.out.println(s);
    }

    @RabbitListener(queues = "queue2")
    public void process2(String s) {
        System.out.println(s);
    }

    @RabbitListener(queues = "queue3")
    public void process3(String s) {
        System.out.println(s);
    }
}
