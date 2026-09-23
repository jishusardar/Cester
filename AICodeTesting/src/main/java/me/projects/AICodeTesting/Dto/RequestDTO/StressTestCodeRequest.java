package me.projects.AICodeTesting.Dto.RequestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StressTestCodeRequest {
    private String UsernameSubject;
    private String Code;
}
