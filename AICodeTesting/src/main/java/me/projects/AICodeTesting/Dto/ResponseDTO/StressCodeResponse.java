package me.projects.AICodeTesting.Dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StressCodeResponse {
    private String Title;
    private List<String> Edgecases;
    private String Message;
    private Double Score;
}
