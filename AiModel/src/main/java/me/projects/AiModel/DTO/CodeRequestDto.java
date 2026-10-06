package me.projects.AiModel.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CodeRequestDto {
    private String UsernameSubject;
    private String Code;
    private String UserInputs;
}
