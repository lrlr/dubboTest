package customer.controller.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author lirui
 * @ClassName: zk 监听事件
 * @Description:
 * @date 2020/6/11 23:56
 */
public class WatcherDemo {
    public static void main(String[] args) throws Exception {
        // PathChildCache：针对于子节点的创建，删除和更新 触发事件
        // NodeCache 针对于当前节点的变化触发事件
        // TreeCache 综合事件
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("49.235.91.141:2181"). //连接地址
                sessionTimeoutMs(15000).  // 连接超时时间
                retryPolicy(new ExponentialBackoffRetry(1000, 1)). // 设置重试策略，
                build();
        curatorFramework.start();
        addListenerWithNode(curatorFramework);
    }

    // 针对当前节点的变化(创建，修改，删除   )触发事件
    // 例如服务注册中心的时候，可以针对服务做动态通知
    private static void addListenerWithNode(CuratorFramework curatorFramework) throws Exception {

        NodeCache nodeCache = new NodeCache(curatorFramework, "/watch", false);
        NodeCacheListener nodeCacheListener = () -> {
            System.out.println("receive Node changed");
            System.out.println(nodeCache.getCurrentData().getPath() + "/" + new String(nodeCache.getCurrentData().getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    // 针对当前节点的子节点变化(创建，修改，删除   )触发事件
    private static void addListenerWithChild(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache nodeCache = new PathChildrenCache(curatorFramework, "/watch", true);
        PathChildrenCacheListener nodeCacheListener = (curatorFramework1, pathChildrenCacheEvent) -> {
            System.out.println(pathChildrenCacheEvent.getType() + "->" + new String(pathChildrenCacheEvent.getData().getData()));

        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start(PathChildrenCache.StartMode.NORMAL);
    }
}
