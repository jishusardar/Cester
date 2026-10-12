package me.projects.AICodeTesting.Dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserChat {
    private String UsernameSubject;
    private String Code;
    private String UserInputs;
}
