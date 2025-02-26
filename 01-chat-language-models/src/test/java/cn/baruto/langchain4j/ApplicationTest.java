package cn.baruto.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @Resource
    private ChatLanguageModel chatLanguageModelOpenAi;

    @BeforeAll
    public static void setUp() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "7890");
    }

    @Test
    public void test1() {
        String generate = chatLanguageModel.chat("你好，你是谁");
        System.out.println(generate);
    }


    @Test
    public void test2() {
        String generate = chatLanguageModelOpenAi.chat("你好，你是谁");
        System.out.println(generate);
    }

    @Test
    public void testChatMessage() {
        UserMessage userMessage = UserMessage.from(TextContent.from("你是谁?")
                //        ,ImageContent.from() User message可以支持多重类型
        );
        ChatResponse response = chatLanguageModel.chat(userMessage);
        System.out.println(response);
    }

}
