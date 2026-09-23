package me.projects.AICodeTesting.Controller;

import me.projects.AICodeTesting.Dto.RequestDTO.StressTestCodeRequest;
import me.projects.AICodeTesting.Dto.ResponseDTO.StressCodeResponse;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseResponseDTO;
import me.projects.AICodeTesting.Service.Testing.TestingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/TestCases")
    public ResponseEntity<TestCaseResponseDTO> GenerateTestcases(@RequestBody StressTestCodeRequest stresstestCodeRequest){
        TestCaseResponseDTO testCaseResponseDTO= testingService.generateTestCases(stresstestCodeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseResponseDTO);
    }
    @PostMapping("/public/Stress")
    public ResponseEntity<StressCodeResponse> StressTestingCode(@RequestBody StressTestCodeRequest stressTestCodeRequest){
       StressCodeResponse stressCodeResponse=testingService.StressTestCode(stressTestCodeRequest);
        return ResponseEntity.ok(stressCodeResponse);
    }
}
