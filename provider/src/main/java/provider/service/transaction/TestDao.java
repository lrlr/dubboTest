package provider.service.transaction;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provider.bean.User;

import java.util.List;
import java.util.Optional;

/**
 * @author lirui
 * @ClassName: 测试Dao
 * @Description:
 * @date 2020/6/3 10:05
 */
@Repository
public interface TestDao extends JpaRepository<User,Integer> {

}
