package me.projects.AICodeTesting.Dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class TestCaseResponseDTO {
    private List<TestCaseDTo> testCaseDTo;
    private String message;

    public TestCaseResponseDTO() {

    }
}
