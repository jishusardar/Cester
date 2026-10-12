package me.projects.AICodeTesting.Service.External;

import me.projects.AICodeTesting.Dto.RequestDTO.UserChat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class AiModelService {
    private final WebClient webClient;

    public AiModelService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://AIMODEL").build();
    }
    public Flux<String> Codechat(UserChat userChat,String userId){
        return webClient.post()
                .uri("/api/AiModel/Codechat")
                .header("X-User-Auth", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_NDJSON)
                .bodyValue(userChat)
                .retrieve()
                .bodyToFlux(String.class);
    }
    public Flux<String> chat(String chat,String userId){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/AiModel/chat")
                        .queryParam("uinput", chat)
                        .build())
                .header("X-User-Auth", userId)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class);
    }

}
