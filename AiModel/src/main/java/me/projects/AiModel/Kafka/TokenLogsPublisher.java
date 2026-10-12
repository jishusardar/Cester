package me.projects.AiModel.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.projects.AiModel.DTO.TokenTransactionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
@Service
@Slf4j
@RequiredArgsConstructor
public class TokenLogsPublisher {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kafka.topics.token-transaction-events}")
    private String TokenTransactionTopic;
    public void TokenLogs(TokenTransactionDTO tokenTransactionDTO){
        try{
            String payload=objectMapper.writeValueAsString(tokenTransactionDTO);
            kafkaTemplate.send(TokenTransactionTopic,tokenTransactionDTO.getUserid(),payload);
        } catch (JacksonException e) {
            log.error("Transaction Dto parsing Failed {}",e.getMessage());
        }
    }
}
