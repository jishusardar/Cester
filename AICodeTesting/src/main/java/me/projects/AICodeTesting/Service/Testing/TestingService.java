package me.projects.AICodeTesting.Service.Testing;
import me.projects.AICodeTesting.Dto.RequestDTO.*;
import me.projects.AICodeTesting.Service.External.AiModelService;
import me.projects.AICodeTesting.Service.External.UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Service
public class TestingService {
    private final UserService userService;
    private AiModelService aiModelService;
    public TestingService(UserService userService,AiModelService aiModelService){
        this.userService = userService;
        this.aiModelService=aiModelService;
    }
    public UserResponseDTO RegisterAndUpdateUser(OidcUser oidcUser){
        String subject =oidcUser.getSubject();
        UserRequestDTO userdto=new UserRequestDTO();
        userdto.setName(oidcUser.getFullName());
        userdto.setUserEmail(oidcUser.getEmail());
        userdto.setProviderSubject(subject);
        return userService.SaveUser("AICODETESTING",userdto);
    }
    public boolean user_have_credits(String id){
        return userService.getUserCredits("AICODETESTING",id).compareTo(new BigDecimal("0.5"))>0;
    }
    public Flux<String> getUserResponseFromCode(UserChatRequest userChatRequest, String userId) {
        UserChat userChat = new UserChat(userId, userChatRequest.getCode(), userChatRequest.getUserInputs());
        return aiModelService.Codechat(userChat,userId);
    }
    public Flux<String> getUserResponse(String chat, String userId) {
        return aiModelService.chat(chat,userId);
    }
}
