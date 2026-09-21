package me.projects.AICodeTesting.Dto.RequestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TestCaseRequestDTO {
    private String Username;
    private String Code;
}
