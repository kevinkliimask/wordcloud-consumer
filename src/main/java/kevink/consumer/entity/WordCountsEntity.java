package kevink.consumer.entity;

import jakarta.persistence.*;
import kevink.consumer.util.HashMapConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "word_counts")
public class WordCountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private UUID messageId;

    @Convert(converter = HashMapConverter.class)
    @Lob
    @Column(name = "word_counts")
    private Map<String, Integer> wordCounts;

    @DateTimeFormat
    @Column
    private Date createdAt;

    public WordCountsEntity(UUID messageId, Map<String, Integer> wordCounts) {
        this.messageId = messageId;
        this.wordCounts = wordCounts;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

}
