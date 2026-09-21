package me.projects.AICodeTesting.Service;

import me.projects.AICodeTesting.Dto.RequestDTO.TestCaseRequestDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserRequestDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserResponseDTO;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseResponseDTO;
import me.projects.AICodeTesting.Service.External.UserService;
import me.projects.AICodeTesting.Service.GenerateTestCases.TestcaseGeneration;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class TestingService {
    private TestcaseGeneration testcaseGeneration;
    private final UserService userService;
    public TestingService(TestcaseGeneration testcaseGeneration, UserService userService){
        this.testcaseGeneration=testcaseGeneration;
        this.userService = userService;
    }
    public TestCaseResponseDTO generateTestCases(TestCaseRequestDTO testCaseRequestDTO){
        TestCaseResponseDTO result=testcaseGeneration.generateTestcases(testCaseRequestDTO);
        return result;
    }
    public UserResponseDTO RegisterAndUpdateUser(OidcUser oidcUser){
        String subject =oidcUser.getSubject();
        UserRequestDTO userdto=new UserRequestDTO();
        userdto.setName(oidcUser.getFullName());
        userdto.setUserEmail(oidcUser.getEmail());
        userdto.setProviderSubject(subject);
        return userService.SaveUser(userdto);
    }
}
