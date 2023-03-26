package kevink.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String POST_QUEUE = "post_message_queue";
    public static final String GET_QUEUE = "get_message_queue";
    public static final String EXCHANGE = "message_exchange";
    public static final String POST_ROUTING_KEY = "post_message_routing_key";
    public static final String GET_ROUTING_KEY = "get_message_routing_key";


    @Bean
    public Queue postQueue() {
        return new Queue(POST_QUEUE);
    }

    @Bean
    public Queue getQueue() {
        return new Queue(GET_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding postBinding(Queue postQueue, TopicExchange exchange) {
        return BindingBuilder.bind(postQueue).to(exchange).with(POST_ROUTING_KEY);
    }

    @Bean
    public Binding getBinding(Queue getQueue, TopicExchange exchange) {
        return BindingBuilder.bind(getQueue).to(exchange).with(GET_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
