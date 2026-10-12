package me.projects.AICodeTesting.Service.External;

import me.projects.AICodeTesting.Dto.RequestDTO.UserChat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@FeignClient(name = "AIMODEL")
public interface AIModelService {
    @PostMapping("/api/AiModel/Codechat")
    Flux<String> Codechat(@RequestBody UserChat userChat,
                                          @RequestHeader("X-User-Auth") String Userid);
    @GetMapping("/api/AiModel/chat")
    Flux<String> chat(@RequestParam String prompt, @RequestHeader("X-User-Auth") String userid);
}
