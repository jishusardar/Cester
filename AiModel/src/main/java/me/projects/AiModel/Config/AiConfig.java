package me.projects.AiModel.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AiConfig {
    @Bean(name = "PromptChatClient")
    public ChatClient GeminiChatClient(GoogleGenAiChatModel googleGenAiChatModel){
        return ChatClient.builder(googleGenAiChatModel).defaultOptions(
                GoogleGenAiChatOptions.builder().
                        maxTokens(20000).temperature(0.8)
                        .model("gemini-3.5-flash-lite")
        ).build();
    }
    @Bean(name = "CodingChatClient")
    public ChatClient GroqChatClient(OpenAiChatModel openAiChatModel){
        return ChatClient.builder(openAiChatModel).defaultOptions(
                OpenAiChatOptions.builder().temperature(1.0).model("qwen/qwen3.8-27b").maxTokens(1000)
        ).build();
    }
    @Bean(name = "ChatMemory")
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
        return MessageWindowChatMemory.builder().chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(40).build();
    }
}
