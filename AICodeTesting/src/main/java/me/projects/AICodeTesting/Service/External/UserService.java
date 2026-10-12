package me.projects.AICodeTesting.Service.External;
import me.projects.AICodeTesting.Dto.RequestDTO.UserRequestDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://USERMANAGEMENT").build();
    }

    public UserResponseDTO SaveUser(String microservice, UserRequestDTO userRequestDTO) {
        return webClient.post()
                .uri("/api/users/save")
                .header("X-Microservice-Name", microservice)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userRequestDTO)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();
    }

    public String getUserBySubject(String microservice, String subject, String usersubject) {
        return webClient.get()
                .uri("/api/users/{usersubject}", usersubject)
                .header("X-Microservice-Name", microservice)
                .header("X-User-Auth", subject)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public BigDecimal getUserCredits(String microservice, String subject) {
        return webClient.get()
                .uri("/api/users/credits")
                .header("X-Microservice-Name", microservice)
                .header("X-User-Auth", subject)
                .retrieve()
                .bodyToMono(BigDecimal.class)
                .block();
    }
}
