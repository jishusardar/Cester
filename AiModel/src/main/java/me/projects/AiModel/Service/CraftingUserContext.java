package me.projects.AiModel.Service;

import lombok.RequiredArgsConstructor;
import me.projects.AiModel.Repository.FetchUserMessgages;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CraftingUserContext {
    private final FetchUserMessgages fetchUserMessgages;
    private final VectorStore vectorStore;
    public void UserContext(String Userid) {
        List<String> usermessagelist=fetchUserMessgages.fetchUserMessages(Userid);
        List<Document> documents=usermessagelist.stream()
                .map(items->{
                    String message_id = Userid + ":" + items;
                    String id= UUID.nameUUIDFromBytes(message_id.getBytes(StandardCharsets.UTF_8)).toString();
                    return new Document(id,items, Map.of("Userid",Userid));
                }).toList();
        this.vectorStore.add(documents);
    }
}
