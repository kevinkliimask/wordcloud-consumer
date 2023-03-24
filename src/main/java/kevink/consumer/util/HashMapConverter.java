package kevink.consumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, Integer>, String> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Integer> wordCounts) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(wordCounts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, Integer> convertToEntityAttribute(String wordCountsJSON) {

        Map<String, Integer> wordCounts = null;
        try {
            wordCounts = objectMapper.readValue(wordCountsJSON,
                    new TypeReference<HashMap<String, Integer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordCounts;
    }
}