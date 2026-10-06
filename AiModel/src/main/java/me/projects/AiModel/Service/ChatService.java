package me.projects.AiModel.Service;

import me.projects.AiModel.DTO.CodeRequestDto;
import me.projects.AiModel.DTO.CodeUserPrompt;
import me.projects.AiModel.Repository.llm_blocked_words;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService implements ChatServiceModel {
    private final ChatClient  codingChatClient;
    private final ChatClient promptChatClient;
    @Value("classpath:prompts/userTokenprompt.st")
    private Resource userTokenPrompt;
    @Value("classpath:prompts/userTokenSystem.st")
    private Resource userTokenSystem;
    @Value("classpath:prompts/System_Debug_code.st")
    private Resource SystemDebugCode;
    @Value("classpath:prompts/User_Request.st")
    private Resource userRequestCode;
    private llm_blocked_words llmBlockedWords;
    private ChatMemory chatMemory;
    @Value("classpath:prompts/UserTokenPromptString.st")
    private Resource userTokenPromptString;
    @Value("classpath:prompts/UserRequestString.st")
    private Resource userRequestString;
    private VectorStore vectorStore;
    private CraftingUserContext craftingUserContext;
    public ChatService(@Qualifier("PromptChatClient") ChatClient promptChatClient,
                       @Qualifier("CodingChatClient") ChatClient codingChatClient, llm_blocked_words llmBlockedWords,
                       ChatMemory chatMemory, CraftingUserContext craftingUserContext, VectorStore vectorStore){
        this.promptChatClient=promptChatClient;
        this.codingChatClient=codingChatClient;
        this.llmBlockedWords=llmBlockedWords;
        this.chatMemory=chatMemory;
        this.craftingUserContext = craftingUserContext;
        this.vectorStore=vectorStore;
    }
    @Override
    public CodeUserPrompt Chat_with_Prompt_Model(CodeRequestDto codeRequestDto, String userid) {
        String code =codeRequestDto.getCode();
        craftingUserContext.UserContext(userid);
        String userInputs = codeRequestDto.getUserInputs();
        return promptChatClient.prompt()
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .advisors(adv->adv.param("chat_memory_conversation_id",userid))
                .system(sys->sys.text(this.userTokenSystem))
                .user(u->u.text(this.userTokenPrompt)
                        .param("code", code)
                        .param("userInputs", userInputs))
                .call().entity(CodeUserPrompt.class);
    }

    @Override
    public String Chat_with_Prompt_Model(String input, String userid) {
        craftingUserContext.UserContext(userid);
        return promptChatClient.prompt()
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .advisors(adv->adv.param("chat_memory_conversation_id",userid))
                .system(sys->sys.text(this.userTokenSystem))
                .user(u->u.text(this.userTokenPromptString)
                        .param("userInputs", input))
                .call().content();
    }

    @Override
    public Flux<String> Chat_With_Model_Code(CodeRequestDto codeRequestDto, String userid) {
        CodeUserPrompt codeUserPrompt=Chat_with_Prompt_Model(codeRequestDto,userid);
        String code = codeUserPrompt.getCode();
        String userInputs = codeUserPrompt.getUserInputs();
        return codingChatClient.prompt()
                .advisors(new SafeGuardAdvisor(llmBlockedWords.FetchListWords()),
                        RetrievalAugmentationAdvisor.builder()
                                .queryTransformers(TranslationQueryTransformer.builder()
                                                .chatClientBuilder(this.codingChatClient.mutate().clone())
                                                .targetLanguage("english").build(),
                                        RewriteQueryTransformer.builder()
                                        .chatClientBuilder(this.codingChatClient.mutate().clone())
                                        .build())
                                        .documentRetriever(VectorStoreDocumentRetriever.builder()
                                        .similarityThreshold(0.5d).topK(4)
                                        .vectorStore(this.vectorStore).build())
                                .queryAugmenter(ContextualQueryAugmenter.builder()
                                        .allowEmptyContext(true).build())
                                .build()
                )
                .advisors(adv->adv.param(
                        VectorStoreDocumentRetriever.FILTER_EXPRESSION,"Userid == '"+userid+"'"))
                .system(sys->sys.text(this.SystemDebugCode))
                .user(
                        u->u.text(this.userRequestCode)
                                .param("code", code)
                                .param("userInputs", userInputs)
                )
                .stream().content();
    }

    @Override
    public Flux<String> Chat_With_Model_Code(String input, String userid) {
        String uinput=Chat_with_Prompt_Model(input,userid);
        return codingChatClient.prompt()
                .advisors(new SafeGuardAdvisor(llmBlockedWords.FetchListWords()),
                        RetrievalAugmentationAdvisor.builder()
                                .queryTransformers(TranslationQueryTransformer.builder()
                                        .chatClientBuilder(this.codingChatClient.mutate().clone())
                                                .targetLanguage("english").build(),
                                        RewriteQueryTransformer.builder()
                                                .chatClientBuilder(this.codingChatClient.mutate().clone())
                                                .build())
                                .documentRetriever(VectorStoreDocumentRetriever.builder()
                                        .similarityThreshold(0.5d).topK(4)
                                        .vectorStore(this.vectorStore).build())
                                .queryAugmenter(ContextualQueryAugmenter.builder()
                                        .allowEmptyContext(true).build())
                                .build()
                )
                .system(sys->sys.text(this.SystemDebugCode))
                .user(
                        u->u.text(this.userRequestString)
                                .param("userInputs", uinput)
                )
                .stream().content();
    }
}
