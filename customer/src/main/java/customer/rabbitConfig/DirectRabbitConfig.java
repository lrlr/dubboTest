package customer.rabbitConfig;/**
 * @ClassName DirectRabbitConfig
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连交换机模式
 */
@Configuration
public class DirectRabbitConfig {
    @Bean
    public Queue directQueue() {
        //队列 起名：TestDirectQueue
        return new Queue("directQueue", true);
    }

    //Direct交换机
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("TestDirectExchange");
    }

    //绑定 将队列和交换机绑定，并设置路由键
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }
}
