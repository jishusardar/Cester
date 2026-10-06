package me.projects.AiModel.Controller;
import me.projects.AiModel.DTO.CodeRequestDto;
import me.projects.AiModel.Service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api/AiModel")
public class ChatController {
    private ChatService chatService;
    public ChatController(ChatService chatService){
        this.chatService=chatService;
    }
    @PostMapping("/Codechat")
    public ResponseEntity<Flux<String>> Codechat(@RequestBody CodeRequestDto codeRequestDto,
                                                 @RequestHeader("X-User-Auth") String Userid){
        Flux<String> resp=chatService.Chat_With_Model_Code(codeRequestDto,Userid);
        return ResponseEntity.ok(resp);
    }
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Hello Nice to see You. Welcome to Cester Share Your Code so that we had Something to talk about.");
    }
    @GetMapping("/chat")
    public ResponseEntity<Flux<String>> chat(@RequestParam("uinput") String prompt,
    @RequestHeader("X-User-Auth") String userid){
        Flux<String> resp=chatService.Chat_With_Model_Code(prompt,userid);
        return ResponseEntity.ok(resp);
    }
}
