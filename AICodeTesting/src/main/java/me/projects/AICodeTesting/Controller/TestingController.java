package me.projects.AICodeTesting.Controller;

import me.projects.AICodeTesting.Dto.RequestDTO.StressTestCodeRequest;
import me.projects.AICodeTesting.Dto.RequestDTO.UserChatRequest;
import me.projects.AICodeTesting.Dto.ResponseDTO.StressCodeResponse;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseResponseDTO;
import me.projects.AICodeTesting.Service.Testing.TestingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/code/Codetalk", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<String>> CodeTalkingModel(
            @RequestBody UserChatRequest userChatRequest,
            @AuthenticationPrincipal OidcUser oidcUser) {
        Flux<String> res = testingService.getUserResponseFromCode(userChatRequest, oidcUser.getSubject());
        return ResponseEntity.ok(res);
    }

    @GetMapping(value = "/code/talk", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<String>> TalkingModel(
            @RequestParam("input") String codes,
            @AuthenticationPrincipal OidcUser oidcUser) {
        Flux<String> res = testingService.getUserResponse(codes, oidcUser.getSubject());
        return ResponseEntity.ok(res);
    }
//    @GetMapping("/public/checkuser")
//    public ResponseEntity<String> checkUsertest(@RequestParam("user") String userid){
//        String res=testingService.getUserBySubject(userid);
//        return ResponseEntity.ok(res);
//    }
}
