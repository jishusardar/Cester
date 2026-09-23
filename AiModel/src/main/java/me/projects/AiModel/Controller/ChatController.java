package me.projects.AiModel.Controller;

import me.projects.AiModel.Service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/AiModel")
public class ChatController {
    private ChatService chatService;
    public ChatController(ChatService chatService){
        this.chatService=chatService;
    }
    @GetMapping("/chat")
    public ResponseEntity<String> Chat(@RequestParam String prompt){
        String resp=chatService.ChatwithModel(prompt);
        return ResponseEntity.ok(resp);
    }
}
