package me.projects.AiModel.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private ChatClient chatClient;
    public ChatService(ChatClient.Builder chatClient){
        this.chatClient=chatClient.build();
    }
    public String ChatwithModel(String prompt){
        Prompt prompt1=new Prompt(prompt);
        String resp=chatClient.prompt(prompt1).system("As you are a Senior Engineer").call().entity(String.class);
        return resp;
    }
}
