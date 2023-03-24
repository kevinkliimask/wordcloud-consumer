package kevink.consumer.entity;

import jakarta.persistence.*;
import kevink.consumer.util.HashMapConverter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Entity
@NoArgsConstructor
public class WordCountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String messageId;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Integer> wordCounts;

    @DateTimeFormat
    private Date createdAt;

    public WordCountsEntity(String messageId, Map<String, Integer> wordCounts) {
        this.messageId = messageId;
        this.wordCounts = wordCounts;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

}
