package me.projects.AICodeTesting.Service.Testing;
import jakarta.annotation.PostConstruct;
import me.projects.AICodeTesting.Dto.RequestDTO.UserChat;
import me.projects.AICodeTesting.Dto.RequestDTO.UserChatRequest;
import me.projects.AICodeTesting.Dto.RequestDTO.UserRequestDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserResponseDTO;
import me.projects.AICodeTesting.Service.External.AIModelService;
import me.projects.AICodeTesting.Service.External.UserService;
import me.projects.AICodeTesting.Service.Tokens.TokenService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Service
public class TestingService {
    private final UserService userService;
    private TokenService tokenService;
    private AIModelService aiModelService;
    private OidcUser oidcUser;
    public TestingService(UserService userService,TokenService tokenService,AIModelService aiModelService){
        this.userService = userService;
        this.tokenService=tokenService;
        this.aiModelService=aiModelService;
    }
    public UserResponseDTO RegisterAndUpdateUser(OidcUser oidcUser){
        String subject =oidcUser.getSubject();
        UserRequestDTO userdto=new UserRequestDTO();
        userdto.setName(oidcUser.getFullName());
        userdto.setUserEmail(oidcUser.getEmail());
        userdto.setProviderSubject(subject);
        this.oidcUser=oidcUser;
        return userService.SaveUser("AICODETESTING",userdto);
    }
    public Flux<String> getUserResponseFromCode(UserChatRequest userChatRequest){
        UserChat userChat=new UserChat(oidcUser.getSubject(),userChatRequest.getCode(),userChatRequest.getUserInputs());
        return aiModelService.Codechat(userChat, userChat.getUsernameSubject());
    }
    public Flux<String> getUserResponse(String Chat){
        String userid=oidcUser.getSubject();
        return aiModelService.chat(Chat,userid);
    }
}
