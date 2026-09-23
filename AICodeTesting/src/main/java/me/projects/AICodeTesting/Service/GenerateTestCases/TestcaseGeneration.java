package me.projects.AICodeTesting.Service.GenerateTestCases;

import me.projects.AICodeTesting.Dto.RequestDTO.StressTestCodeRequest;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseDTo;
import me.projects.AICodeTesting.Dto.ResponseDTO.TestCaseResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TestcaseGeneration {
    public TestCaseResponseDTO generateTestcases(StressTestCodeRequest stresstestCodeRequest){
        System.out.println(stresstestCodeRequest.getCode());
        List<TestCaseDTo> arr=new ArrayList<>();
        TestCaseResponseDTO result=new TestCaseResponseDTO(arr,"Hello Here is Your TestCases");
        return result;
    }
}
