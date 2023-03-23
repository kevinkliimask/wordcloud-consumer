package kevink.consumer.consumer;

import kevink.consumer.message.MQConfig;
import kevink.consumer.message.ReplyMessage;
import kevink.consumer.message.UploadMessage;
import kevink.consumer.service.WordCloudService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final WordCloudService wordCloudService;

    public MessageListener(WordCloudService wordCloudService) {
        this.wordCloudService = wordCloudService;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public ReplyMessage listener(UploadMessage message) {
        return new ReplyMessage(wordCloudService.handleMessage(message));
    }

}
