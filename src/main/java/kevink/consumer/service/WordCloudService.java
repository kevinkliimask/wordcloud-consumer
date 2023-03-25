package kevink.consumer.service;

import kevink.consumer.entity.WordCountsEntity;
import kevink.consumer.message.UploadMessage;
import kevink.consumer.repository.WordCountsRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordCloudService {

    private final HashSet<String> excludedWords;
    private final WordCountsRepository wordCountsRepository;

    public WordCloudService(WordCountsRepository wordCountsRepository) throws IOException {
        InputStream inputStream = new ClassPathResource("ExcludedWords.txt").getInputStream();
        excludedWords = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().collect(Collectors.toCollection(HashSet::new));
        this.wordCountsRepository = wordCountsRepository;
    }

    public HashMap<String, Integer> handleMessage(UploadMessage message) {
        String text = new String(Base64.getDecoder().decode(message.getMessageData().getBytes()));
        HashMap<String, Integer> wordCounts = findWordCounts(text);
        wordCountsRepository.save(new WordCountsEntity(UUID.fromString(message.getMessageId()), wordCounts));
        return wordCounts;
    }

    private HashMap<String, Integer> findWordCounts(String text) {
        String[] wordsArray = text.trim().split("\\s+");
        HashMap<String, Integer> wordCounts = new HashMap<>();
        for (String word : wordsArray) {
            if (!excludedWords.contains(word.toLowerCase()))
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        return wordCounts;
    }

}
