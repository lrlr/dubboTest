package provider.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import provider.bean.User;

/**
 * @author lirui
 * @ClassName: 事务服务测试1
 * @Description:
 * @date 2020/6/3 17:19
 */
@Service
public class TransactionTestService1 {
    @Autowired
    private TestDao testDao;
    @Transactional(propagation = Propagation.NESTED)
//    @Transactional
    public void test1() {
        User user = new User();
        user.setUid(1);
        user.setUsername("11");
        user.setPassword("11");
        User save = testDao.save(user);
        System.out.println(save.getUsername());
        throw new RuntimeException();
    }

        @Transactional
    public void test2() {
        User user = new User();
        user.setUid(2);
        user.setUsername("test2");
        user.setPassword("1234");
        testDao.save(user);
    }
}
