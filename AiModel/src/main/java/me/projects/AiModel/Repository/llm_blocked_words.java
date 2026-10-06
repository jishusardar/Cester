package me.projects.AiModel.Repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.projects.AiModel.DTO.WordsRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class llm_blocked_words{
    private final JdbcTemplate jdbcTemplate;
    String wordquery= """
            SELECT Word from LLM_BLOCKED_WORDS WHERE Is_deleted=FALSE
            """;
    public List<String> FetchListWords(){
        return jdbcTemplate.query(wordquery,new WordsRowMapper());
    }
}
