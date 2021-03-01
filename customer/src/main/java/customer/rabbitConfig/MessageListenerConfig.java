package customer.rabbitConfig;

import customer.service.DirectReciver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MessageListenerConfig
 * @Description: TODO 
 * @Author lirui
 * @Date 2020/3/12 
 * @Version V1.0
**/
@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    DirectRabbitConfig directRabbitConfig;
    @Autowired
    DirectReciver directReceiver;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container=new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);//RabbitMQ默认是自动确认，这里改为手动确认
        container.setQueues(directRabbitConfig.directQueue());
        container.setMessageListener(directReceiver);
        return container;
    }
}
