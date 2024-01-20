package provider.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang.time.DateFormatUtils;
import org.openjdk.jol.info.ClassLayout;
import provider.UTIL.JsonUtil;
import provider.bean.MaskActivityTaskBaseVo;
import provider.bean.TestTask;
import provider.bean.UserReadTimeInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TestDemo
 * @Description: TODO
 * @Author lirui
 * @Date 2020/5/2
 * @Version V1.0
 **/
public class TestDemo {
    private static HeadObject headObject = new HeadObject();
    public static final long ONE_MINUTE_MILLISECONDS = 60000;

    public static void main(String[] args) {
        String time = "2022-06-18 13:04:30";
        Date date = DateUtil.parse(time);
        //判断活动是否结束
        if (!DateTime.now().isBefore(date)) {
            System.out.println("活动结束了");
        }

        long readMinuteSum = 0;
        for (int i = 0; i < 3; i++) {
            UserReadTimeInfo userReadTimeInfo = new UserReadTimeInfo();
            userReadTimeInfo.setReadTime(2900000);
            long readMinute = (long) Math.ceil(userReadTimeInfo.readTime / ONE_MINUTE_MILLISECONDS);
            readMinuteSum = readMinuteSum + readMinute;
        }
        System.out.println("readMinuteSum" + JSONUtil.toJsonStr(readMinuteSum));
        Date dates = DateUtil.parse("2023-10-15 09:55:55");
        DateTime.now().isBefore(dates);
        String json = "[\n" +
                "    {\n" +
                "        \"activityCode\":\"maskStar01\",\n" +
                "        \"taskList\":[\n" +
                "            {\n" +
                "                \"taskName\":\"阅读活动书籍15分钟以上\",\n" +
                "                \"taskRemark\":\"最多可获得1张潇湘票\",\n" +
                "                \"taskType\":1,\n" +
                "                \"taskCode\":\"readBook15\",\n" +
                "                \"taskOverProcess\":1,\n" +
                "                \"taskUrl\":\"www.baidu.com\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"taskName\":\"参与讨论，发布话题10字以上\",\n" +
                "                \"taskRemark\":\"最多可获得1张潇湘票\",\n" +
                "                \"taskType\":1,\n" +
                "                \"taskCode\":\"discussRelease\",\n" +
                "                \"taskOverProcess\":1,\n" +
                "                \"taskUrl\":\"www.baidu.com\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"taskName\":\"在活动书籍中发布书评或段评\",\n" +
                "                \"taskRemark\":\"最多可获得1张潇湘票\",\n" +
                "                \"taskType\":1,\n" +
                "                \"taskCode\":\"postComment\",\n" +
                "                \"taskOverProcess\":1,\n" +
                "                \"taskUrl\":\"www.baidu.com\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"taskName\":\"邀请组队赢抽奖机会\",\n" +
                "                \"taskRemark\":\"邀请成功1人，将直接获得一次抽奖机会\",\n" +
                "                \"taskType\":2,\n" +
                "                \"taskCode\":\"discussRelease\",\n" +
                "                \"taskOverProcess\":1,\n" +
                "                \"taskUrl\":\"www.baidu.com\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"taskName\":\"在投票/提名蒙面大咖,x月x日公布答案猜中抽好礼\",\n" +
                "                \"taskRemark\":\"每投票或提名成功1次均送抽奖机会\",\n" +
                "                \"taskType\":1,\n" +
                "                \"taskCode\":\"discussRelease\",\n" +
                "                \"taskOverProcess\":1,\n" +
                "                \"taskUrl\":\"www.baidu.com\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
//       List<TestTask> tasks=  JsonUtil.fromJson(json,List.class);
//       JSONArray array = JSONUtil.parseArray(json);
//        List<TestTask> tasks=    JSONUtil.toList(array,TestTask.class);

        String s = "[ {   \"activityCode\":\"maskStar01\",    \"taskList\":[   {  \"taskName\":\"阅读活动书籍15分钟以上\",  \"taskRemark\":\"最多可获得1张潇湘票\",  \"taskType\": 1,  \"taskCode\":\"readBook15\",  \"taskOverProcess\":1,  \"taskUrl\":\"www.baidu.com\"}, {  \"taskName\":\"参与讨论，发布话题10字以上\",  \"taskRemark\":\"最多可获得1张潇湘票\",  \"taskType\": 1,  \"taskCode\":\"discussRelease\",  \"taskOverProcess\":1,  \"taskUrl\":\"www.baidu.com\"}, {  \"taskName\":\"在活动书籍中发布书评或段评\",  \"taskRemark\":\"最多可获得1张潇湘票\",  \"taskType\": 1,  \"taskCode\":\"postComment\",  \"taskOverProcess\":1,  \"taskUrl\":\"www.baidu.com\"}, {  \"taskName\":\"邀请组队赢抽奖机会\",  \"taskRemark\":\"邀请成功1人，将直接获得一次抽奖机会\",  \"taskType\": 2,  \"taskCode\":\"inviteTerm\",  \"taskOverProcess\":1,  \"taskUrl\":\"www.baidu.com\"}, {  \"taskName\":\"在投票/提名蒙面大咖,x月x日公布答案猜中抽好礼\",  \"taskRemark\":\"每投票或提名成功1次均送抽奖机会\",  \"taskType\": 1,  \"taskCode\":\"voteMask\",  \"taskOverProcess\":1,  \"taskUrl\":\"www.baidu.com\"}]}]";
        JSONArray array = JSONUtil.parseArray(s);
        //获取配置的基础任务数据
        List<MaskActivityTaskBaseVo> tasks = JSONUtil.toList(array, MaskActivityTaskBaseVo.class);
//        System.out.println(tasks.get(0).getMaskTaskBaseVoList());

        MaskActivityTaskBaseVo vo = new MaskActivityTaskBaseVo();
        if (vo.activityCode == "") {
            System.out.println("sssssssssssss");
        }
        List<MaskActivityTaskBaseVo> taskss = new ArrayList<>();
        MaskActivityTaskBaseVo vo1 = new MaskActivityTaskBaseVo();
        vo1.setBookId(101010L);
        long book = 101010;
        taskss.add(vo1);
        ArrayList<Long> bookList = (ArrayList<Long>) taskss.stream().map(MaskActivityTaskBaseVo::getBookId).collect(Collectors.toList());
        if (bookList.contains(book)) {
            System.out.println("sss");
        }

//        System.out.println(JsonUtil.string(tasks));
//        System.out.println(DateTime.now().isBefore(date));
//        System.out.println(date);
        //  System.out.println(ClassLayout.parseInstance(headObject).toPrintable());
    }
}
