package provider.controller.RedisController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName RedisLikeRank
 * @Description: Redis实现文章点赞并按点赞数排行
 * @Author lirui
 * @Date 2020/5/26
 * @Version V1.0
 **/
@RestController
@RequestMapping("/redisLike")
public class RedisLikeRankController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/likeRank")
    public void ss() {
        //创建3篇文章，用hash存储
        redisTemplate.opsForHash().put("pape:01", "title", "文章1");
        redisTemplate.opsForHash().put("pape:02", "title", "文章2");
        redisTemplate.opsForHash().put("pape:03", "title", "文章3");

        Map<String, String> entries = redisTemplate.opsForHash().entries("pape:01");
        System.out.println(entries);
        //创建有序集合，保存文章点赞排名
        redisTemplate.opsForZSet().add("score", "pape:01", 0);
        redisTemplate.opsForZSet().add("score", "pape:02", 0);
        redisTemplate.opsForZSet().add("score", "pape:03", 0);
//              String
        System.out.println(redisTemplate.opsForZSet().range("score", 0, -1).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        redisTemplate.opsForZSet().incrementScore("score", "pape:01", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:02", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:03", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:01", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:02", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:01", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:03", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:03", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:02", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:03", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:01", 1);
        redisTemplate.opsForZSet().incrementScore("score", "pape:01", 1);

        //获取排行榜
        List<Map<String, Object>> rankList = getRankList();
        for (Map<String, Object> map : rankList) {
            System.out.println("page" + map.get("page") + "count" + map.get("count") + "rank" + map.get("rank"));
        }
    }


    // 获取排行榜
    private List<Map<String, Object>> getRankList() {
        List<Map<String, Object>> rank = new ArrayList<>();
        //只取前十条
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("score", 0, 99999, 0, 10);
        int index = 0;
        for (ZSetOperations.TypedTuple typle : typedTuples) {
            Map<String, Object> userRankMap = new HashMap<>();
            userRankMap.put("page", typle.getValue());
            userRankMap.put("count", typle.getScore().intValue());
            userRankMap.put("rank", ++index);
            rank.add(userRankMap);
        }
        return rank;
    }


    /**
     * 功能描述 获取 我的 排名信息
     */
    private Map<String, Object> getUserRank(String userId) {
        //我的排名
        Long userRank = redisTemplate.opsForZSet().reverseRank("score", userId);
        //我的分数
        Double count = redisTemplate.opsForZSet().score("score", userId);
        Map<String, Object> userRankMap = new HashMap<>();
        userRankMap.put("userId", userId);
        userRankMap.put("count", count.intValue());
        userRankMap.put("rank", userRank + 1);
        String userInfo = (String) redisTemplate.opsForValue().get("score" + "_" + userId);
        return userRankMap;
    }
}
