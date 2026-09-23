package me.projects.AICodeTesting.Service.TokenManagement;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Service
public class TokenService {
    Map<String, BigDecimal> Tokenstack=new HashMap<>();

    public TokenService(){
        Tokenstack.put("STG",BigDecimal.valueOf(2));
        Tokenstack.put("GC",BigDecimal.valueOf(1));
    }
    public BigDecimal TokenNeed(String s){
        return Tokenstack.get(s);
    }
}
