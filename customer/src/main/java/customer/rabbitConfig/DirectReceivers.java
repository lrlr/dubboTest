package customer.rabbitConfig;

import com.rabbitmq.client.Channel;
import com.sun.javafx.collections.MappingChange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @ClassName DirectReceiver
 * @Description: TODO
 * @Author lirui
 * @Date 2020/3/12
 * @Version V1.0
 **/
@Component
public class DirectReceivers implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliverTag = message.getMessageProperties().getDeliveryTag();
        String msg = message.toString();
        String[] msgArray = msg.split("'");
        HashMap<String, String> msgMap = mapStringToMap(msgArray[1].trim());
        String messageId = msgMap.get("messageId");
        String messageData = msgMap.get("messageData");
        String createTime = msgMap.get("createTime");
        System.out.println("messageId:" + messageId + "messageData" + messageData + "createTime" + createTime);
        channel.basicAck(deliverTag, true);
    }

    //{key=value,key=value,key=value} 格式转换成map
    private HashMap<String, String> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        HashMap<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
