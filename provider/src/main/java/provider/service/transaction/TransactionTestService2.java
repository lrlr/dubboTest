package provider.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import provider.bean.User;

/**
 * @author lirui
 * @ClassName: 测试事务2
 * @Description:
 * @date 2020/6/3 17:19
 */
@Service
public class TransactionTestService2 {
    @Autowired
    private TestDao testDao;

    @Transactional
    public void test3() {
        User user = new User();
        user.setUid(3);
        user.setUsername("333");

        user.setPassword("333");
        testDao.save(user);
    }
}
