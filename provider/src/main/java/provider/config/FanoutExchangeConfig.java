package provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanoutExchange
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/
@Configuration
public class FanoutExchangeConfig {
    @Bean
    Queue queue1() {
        return new Queue("queue1");
    }

    @Bean
    Queue queue2() {
        return new Queue("queue2");
    }

    @Bean
    Queue queue3() {
        return new Queue("queue3");
    }
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }
    @Bean
    FanoutExchange fanout2Exchange(){
        return new FanoutExchange("fanout2Exchange");
    }
    @Bean
    Binding bind1(){
      return  BindingBuilder.bind(queue1()).to(fanoutExchange());
    }
    @Bean
    Binding bind2(){
        return  BindingBuilder.bind(queue2()).to(fanoutExchange());
    }
    @Bean
    Binding bind3(){
        return  BindingBuilder.bind(queue3()).to(fanoutExchange());
    }
}
