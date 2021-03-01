package provider.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Backoff;

/**
 * @ClassName RabbitConfig
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/
@Configuration
public class RabbitConfig {
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数，无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback" + "相关数据： " + correlationData);
                System.out.println("ConfirmCallback" + "确认情况" + ack);
                System.out.println("ConfirmCallback" + "原因" + cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("ReturnCallback:     " + "消息：" + message);
                System.out.println("ReturnCallback:     " + "回应码：" + i);
                System.out.println("ReturnCallback:     " + "回应信息：" + s);
                System.out.println("ReturnCallback:     " + "交换机：" + s1);
                System.out.println("ReturnCallback:     " + "路由键：" + s2);
            }
        });


        return rabbitTemplate;
    }
}
