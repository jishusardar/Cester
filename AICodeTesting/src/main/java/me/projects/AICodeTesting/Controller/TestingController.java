package me.projects.AICodeTesting.Controller;

import me.projects.AICodeTesting.Dto.RequestDTO.TestCaseRequestDTO;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseResponseDTO;
import me.projects.AICodeTesting.Service.TestingService;
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

    @PostMapping
    public ResponseEntity<TestCaseResponseDTO> generateTestCases(@RequestBody TestCaseRequestDTO testCaseRequestDTO){
        TestCaseResponseDTO testCaseResponseDTO= testingService.generateTestCases(testCaseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseResponseDTO);
    }
}
