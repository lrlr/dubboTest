package customer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import customer.controller.TestHandlerMethodArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @ClassName ProviderApp
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/10
 * @Version V1.0
 **/

@SpringBootApplication
@EnableDubbo
public class CustomerApp extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(CustomerApp.class, args);
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        super.addArgumentResolvers(argumentResolvers);
//
//        argumentResolvers.add(new TestHandlerMethodArgumentResolver());
//    }
}
