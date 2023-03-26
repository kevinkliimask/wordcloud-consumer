package kevink.consumer.listener;

import kevink.consumer.config.MQConfig;
import kevink.consumer.message.GetWordCountsMessage;
import kevink.consumer.message.GetWordCountsReplyMessage;
import kevink.consumer.message.UploadReplyMessage;
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

    @RabbitListener(queues = MQConfig.POST_QUEUE)
    public UploadReplyMessage fileUploadListener(UploadMessage message) {
        return new UploadReplyMessage(wordCloudService.handleFileUpload(message));
    }

    @RabbitListener(queues = MQConfig.GET_QUEUE)
    public GetWordCountsReplyMessage getWordCountsListener(GetWordCountsMessage message) {
        return new GetWordCountsReplyMessage(wordCloudService.handleGetResult(message));
    }

}
