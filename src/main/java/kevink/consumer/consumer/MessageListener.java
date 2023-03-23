package kevink.consumer.consumer;

import kevink.consumer.message.MQConfig;
import kevink.consumer.message.UploadMessage;
import kevink.consumer.service.WordCloudService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class MessageListener {

    private final WordCloudService wordCloudService;

    public MessageListener(WordCloudService wordCloudService) {
        this.wordCloudService = wordCloudService;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public HashMap<String, Integer> listener(UploadMessage message) {
        return wordCloudService.handleMessage(message);
    }

}
