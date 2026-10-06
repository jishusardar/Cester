package me.projects.AiModel.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CodeResponseDto {
    private String Problem_Summary;
    private String Analysis;
    private String Recommendation;
    private String Code;
}
