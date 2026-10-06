package me.projects.AiModel;

import me.projects.AiModel.Repository.FetchUserMessgages;
import me.projects.AiModel.Repository.llm_blocked_words;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AiModelApplicationTests {
	@Autowired
	private llm_blocked_words llmBlockedWords;
	@Test
	void contextLoads() {

	}
//	@Test
//	void TestBlockedWords(){
//        List<String> res=llmBlockedWords.FetchListWords();
//		for (int i=0;i<res.size();i++){
//			System.out.println(res.get(i));
//		}
//	}
//	@Autowired
//	private FetchUserMessgages fetchUserMessgages;
//	@Test
//	void TestUserMessages(){
//		List<String> messgaes=fetchUserMessgages.fetchUserMessages("11012");
//		for (int i=0;i<messgaes.size();i++){
//			System.out.println(messgaes.get(i));
//		}
//	}

}
