package me.projects.AICodeTesting.Controller;

import me.projects.AICodeTesting.Dto.RequestDTO.StressTestCodeRequest;
import me.projects.AICodeTesting.Dto.RequestDTO.UserChatRequest;
import me.projects.AICodeTesting.Dto.ResponseDTO.StressCodeResponse;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseResponseDTO;
import me.projects.AICodeTesting.Service.Testing.TestingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/testing")
public class TestingController {
    private TestingService testingService;

    public TestingController(TestingService testingService) {
        this.testingService = testingService;
    }
    @GetMapping("/home")
    public String Home(@AuthenticationPrincipal OidcUser oidcUser){
        return "Welcome Home "+oidcUser.getFullName();
    }

    @PostMapping("/code/Codetalk")
    public ResponseEntity<Flux<String>> CodeTalkingModel(@RequestBody UserChatRequest userChatRequest){
        Flux<String> res= testingService.getUserResponseFromCode(userChatRequest);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/code/talk")
    public ResponseEntity<Flux<String>> TalkingModel(@RequestParam("input") String codes){
       Flux<String>res=testingService.getUserResponse(codes);
        return ResponseEntity.ok(res);
    }
//    @GetMapping("/public/checkuser")
//    public ResponseEntity<String> checkUsertest(@RequestParam("user") String userid){
//        String res=testingService.getUserBySubject(userid);
//        return ResponseEntity.ok(res);
//    }
}
