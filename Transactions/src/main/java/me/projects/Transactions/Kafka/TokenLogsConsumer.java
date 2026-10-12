package me.projects.Transactions.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.projects.Transactions.Entity.Transactions;
import me.projects.Transactions.Service.TransactionService;
import me.projects.Transactions.dto.TransactionRequestDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenLogsConsumer {
    private final ObjectMapper objectMapper;
    private final TransactionService transactionService;
    @KafkaListener(topics = "${kafka.topics.token-transaction-events}",groupId = "${spring.kafka.consumer.group-id}")
    public void TokenAudits(String payload){
        try {
            TransactionRequestDTO transactionRequestDTO=objectMapper.readValue(payload,TransactionRequestDTO.class);
            transactionService.createTransaction(map(transactionRequestDTO));
        } catch (JacksonException e) {
            log.error("The Transaction Parsing is invalid {}",e.getMessage());
        }
    }
    public Transactions map(TransactionRequestDTO transactionRequestDTO){
        return new Transactions(transactionRequestDTO.getUserid(),
                transactionRequestDTO.getAmount(),transactionRequestDTO.getLocalDateTime(),
                transactionRequestDTO.getStatus());
    }
}
