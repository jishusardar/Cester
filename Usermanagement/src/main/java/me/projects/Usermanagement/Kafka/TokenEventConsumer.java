package me.projects.Usermanagement.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.projects.Usermanagement.DTo.UserCredituseDTO;
import me.projects.Usermanagement.Service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenEventConsumer {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    @KafkaListener(topics = "${kafka.topics.token-events}",groupId ="${spring.kafka.consumer.group-id}" )
    public void onTokenEvents(String payload){
        try{
            UserCredituseDTO userCredituseDTO=objectMapper.readValue(payload,UserCredituseDTO.class);
            userService.subtractUserCredit(userCredituseDTO);
        } catch (JacksonException e) {
            log.error("JSON Parsing is invalidated {}",e.getMessage());
        }
    }
}
