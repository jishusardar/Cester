package me.projects.AICodeTesting.Service.StressTesting;

import me.projects.AICodeTesting.Dto.RequestDTO.StressTestCodeRequest;
import me.projects.AICodeTesting.Dto.ResponseDTO.StressCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class StressTestingCode {
    public StressCodeResponse stressTestingCode(StressTestCodeRequest stressTestCodeRequest){
        return new StressCodeResponse();
    }
}
