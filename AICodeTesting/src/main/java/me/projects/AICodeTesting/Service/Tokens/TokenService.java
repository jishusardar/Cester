package me.projects.AICodeTesting.Service.Tokens;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.projects.AICodeTesting.Dto.RequestDTO.UserCreditDTO;
import me.projects.AICodeTesting.Service.External.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Scope("prototype")
public class TokenService {
    private UserService userService;
    public TokenService(UserService userService){
        this.userService=userService;
    }
    public BigDecimal getUserCredits(String Userid){
        return userService.getUserCredits("AICODETESTING",Userid);
    }
    public void usedCredits(BigDecimal credits,String Userid){
        UserCreditDTO userCreditDTO=new UserCreditDTO(Userid,credits);
        userService.subtractUserCredit("AICODETESTING",Userid,userCreditDTO);
    }
    public void addCredits(BigDecimal credits,String Userid){
        UserCreditDTO userCreditDTO=new UserCreditDTO(Userid,credits);
        userService.AddUserCredits("AICODETESTING",Userid,userCreditDTO);
    }
    public boolean user_have_credits(String id){
        return userService.getUserCredits("AICODETESTING",id).compareTo(new BigDecimal("0.5"))>0;
    }
}
