package me.projects.AiModel.Repository;

import lombok.RequiredArgsConstructor;
import me.projects.AiModel.DTO.MessagesWrapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class FetchUserMessgages {
    private final JdbcTemplate jdbcTemplate;

    String querry= """
            SELECT content FROM spring_ai_chat_memory WHERE conversation_id=? ORDER BY timestamp DESC LIMIT 3
            """;
    public List<String> fetchUserMessages(String Userid){
        return jdbcTemplate.query(querry, new MessagesWrapper(),Userid);
    }
}
