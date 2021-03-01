package customer.controller.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lirui
 * @ClassName: zookeeper练习
 * @Description:
 * @date 2020/6/11 23:01
 */
public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("49.235.91.141:2181"). //连接地址
                sessionTimeoutMs(15000).  // 连接超时时间
                retryPolicy(new ExponentialBackoffRetry(1000, 1)). // 设置重试策略，
                build();
        curatorFramework.start();

        new CuratorTest().createData(curatorFramework);
    }

    // 设置权限

    public void createAcl(CuratorFramework curatorFramework) throws Exception {
        List<ACL> list = new ArrayList<>();
        ACL acl = new ACL(ZooDefs.Perms.READ, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        list.add(acl);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(list).forPath("/auth");

    }
    // 修改权限

    public void updateAcl(CuratorFramework curatorFramework) throws Exception {
        List<ACL> list = new ArrayList<>();
        ACL acl = new ACL(ZooDefs.Perms.READ|ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        list.add(acl);
        curatorFramework.setACL().withACL(list).forPath("/data/program");

    }
    // 创建节点
    public void createData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create()
                .creatingParentsIfNeeded() //设置是否自动创建需要的父节点
                .withMode(CreateMode.PERSISTENT)//设置节点类型，节点有持久节点，临时节点，这里进行设置
                .forPath("/data/programs", "test".getBytes());//创建节点的具体设置以及值
    }

    // 更新节点
    public void updateData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData()
                .forPath("/data/program", "test".getBytes());
    }

    // 删除节点
    public void deleteData(CuratorFramework curatorFramework) throws Exception {
        Stat stat = new Stat();// 版本号
        //获取值的同时得到当前的版本号
        String value = new String(curatorFramework.getData().storingStatIn(stat).forPath("data/program"));

        curatorFramework.delete().withVersion(stat.getVersion()) //传入版本号，如果版本号不对就删除不了
                .forPath("/data/program");
    }

}
