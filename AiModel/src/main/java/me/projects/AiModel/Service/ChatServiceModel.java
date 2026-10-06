package me.projects.AiModel.Service;

import me.projects.AiModel.DTO.CodeRequestDto;
import me.projects.AiModel.DTO.CodeUserPrompt;
import reactor.core.publisher.Flux;

public interface ChatServiceModel {
    CodeUserPrompt Chat_with_Prompt_Model(CodeRequestDto codeRequestDto, String userid);
    String Chat_with_Prompt_Model(String input,String userid);
    Flux<String> Chat_With_Model_Code(CodeRequestDto codeRequestDto, String userid);
    Flux<String> Chat_With_Model_Code(String input,String userid);
}
