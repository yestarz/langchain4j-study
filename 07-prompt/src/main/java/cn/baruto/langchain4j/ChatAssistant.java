package cn.baruto.langchain4j;

import cn.baruto.langchain4j.prompt.LegalPrompt;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * 类似于OpenFeign的调用形式
 */
public interface ChatAssistant {

    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    @UserMessage("请回答以下法律问题：{{question}}")
    String chat1(@V("question") String question);

    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    String chat2(LegalPrompt prompt);

}
