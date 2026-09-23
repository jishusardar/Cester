package me.projects.AICodeTesting.Service.Testing;

import me.projects.AICodeTesting.Dto.RequestDTO.StressTestCodeRequest;
import me.projects.AICodeTesting.Dto.RequestDTO.UserCredituseDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserRequestDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserResponseDTO;
import me.projects.AICodeTesting.Dto.ResponseDTO.StressCodeResponse;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseResponseDTO;
import me.projects.AICodeTesting.Service.External.UserService;
import me.projects.AICodeTesting.Service.GenerateTestCases.TestcaseGeneration;
import me.projects.AICodeTesting.Service.StressTesting.StressTestingCode;
import me.projects.AICodeTesting.Service.TokenManagement.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TestingService {
    private TestcaseGeneration testcaseGeneration;
    private final UserService userService;
    private StressTestingCode stressTestingCode;
    private TokenService tokenService;
    public TestingService(TestcaseGeneration testcaseGeneration, UserService userService,StressTestingCode stressTestingCode,TokenService tokenService){
        this.testcaseGeneration=testcaseGeneration;
        this.userService = userService;
        this.stressTestingCode=stressTestingCode;
        this.tokenService=tokenService;
    }
    public TestCaseResponseDTO generateTestCases(StressTestCodeRequest stresstestCodeRequest){
        TestCaseResponseDTO result=testcaseGeneration.generateTestcases(stresstestCodeRequest);
        return result;
    }
    public UserResponseDTO RegisterAndUpdateUser(OidcUser oidcUser){
        String subject =oidcUser.getSubject();
        UserRequestDTO userdto=new UserRequestDTO();
        userdto.setName(oidcUser.getFullName());
        userdto.setUserEmail(oidcUser.getEmail());
        userdto.setProviderSubject(subject);
        return userService.SaveUser("AICODETESTING",userdto);
    }
    public StressCodeResponse StressTestCode(StressTestCodeRequest stressTestCodeRequest){
        String Userid=stressTestCodeRequest.getUsernameSubject();
        BigDecimal Usercredit=userService.getUserCredits("AICODETESTING",
                stressTestCodeRequest.getUsernameSubject());
        BigDecimal RequiredCredits=tokenService.TokenNeed("STG");
        if(RequiredCredits.equals(Usercredit)|| Usercredit.compareTo(RequiredCredits)>0){
            UserCredituseDTO userCredituseDTO=new UserCredituseDTO(Userid,RequiredCredits);
            userService.subtractUserCredit("AICODETESTING",stressTestCodeRequest.getUsernameSubject(),userCredituseDTO);
            StressCodeResponse stressCodeResponse=stressTestingCode.stressTestingCode(stressTestCodeRequest);
            stressCodeResponse.setTitle("Test Message");
            stressCodeResponse.setMessage("TestMessage");
            return stressCodeResponse;
        }
        else{
            StressCodeResponse stressCodeResponse=new StressCodeResponse();
            stressCodeResponse.setMessage("Dont have Enough Token");
            return stressCodeResponse;
        }
    }
    public boolean getUserBySubject(String subject){
        return userService.getUserBySubject("AICODETESTING",subject,subject);
    }
}
