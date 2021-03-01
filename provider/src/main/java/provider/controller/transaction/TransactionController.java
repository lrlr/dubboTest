package provider.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.bean.User;
import provider.service.transaction.TestDao;
import provider.service.transaction.TransactionTestService1;
import provider.service.transaction.TransactionTestService2;

import java.util.List;

/**
 * @author lirui
 * @ClassName: Spring事务Controller
 * @Description:
 * @date 2020/6/3 10:13
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TestDao dao;

    @Autowired
    TransactionTestService1 service1;

    @Autowired
    TransactionTestService2 service2;

    @GetMapping("/test1")
    @Transactional
    public void test() {
        User user = new User();
        user.setUid(2);
        user.setUsername("22");
        user.setPassword("22");
        dao.save(user);

        service2.test3();
        try {
            service1.test1();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        throw new RuntimeException();
    }
}
