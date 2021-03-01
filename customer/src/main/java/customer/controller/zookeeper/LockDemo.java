package customer.controller.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author lirui
 * @ClassName: zk实现分布式锁
 * @Description:
 * @date 2020/6/16 22:13
 */
public class LockDemo {
    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("49.235.91.141:2181"). //连接地址
                sessionTimeoutMs(15000).  // 连接超时时间
                retryPolicy(new ExponentialBackoffRetry(1000, 1)). // 设置重试策略，
                build();
        curatorFramework.start();
        final InterProcessMutex lock=new InterProcessMutex(curatorFramework,"/locks");
        for (int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"->尝试竞争锁");
                try{
                    lock.acquire();
                }catch (Exception e){
                    System.out.println(Thread.currentThread().getName()+"成功获得锁");
                }finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
