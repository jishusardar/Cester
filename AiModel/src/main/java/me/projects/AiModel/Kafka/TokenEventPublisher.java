package me.projects.AiModel.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.projects.AiModel.DTO.UserCredituseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kafka.topics.token-events}")
    private String TokenEventsTopics;
    public void publishTokenEvent(UserCredituseDTO userCredituseDTO){
        try{
            String payload=objectMapper.writeValueAsString(userCredituseDTO);
            kafkaTemplate.send(TokenEventsTopics,userCredituseDTO.getUserid(),payload);
        } catch (JacksonException e) {
            log.error("String Parsing is invalidated {}",e.getMessage());
        }
    }
}
