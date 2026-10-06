package me.projects.AiModel.Advisors;

import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;

import java.util.List;

public class Blocked_Word_Advisor extends SafeGuardAdvisor {
    public Blocked_Word_Advisor(List<String> sensitiveWords) {
        super(sensitiveWords);
    }

    public Blocked_Word_Advisor(List<String> sensitiveWords, String failureResponse, int order) {
        super(sensitiveWords, failureResponse, order);
    }
}
