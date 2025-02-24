package cn.baruto.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ChatApiConfigApplication.class)
public class ChatApiConfigApplicationTest {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @Resource
    private ChatLanguageModel chatLanguageModelOpenAi;

    @Resource
    private ChatAssistant chatAssistant;

    @BeforeAll
    public static void setUp(){
        System.setProperty("http.proxyHost","127.0.0.1");
        System.setProperty("http.proxyPort","7890");
    }

    @Test
    public void test1(){
        String generate = chatLanguageModel.generate("你好，你是谁");
        System.out.println(generate);
    }


    @Test
    public void test2(){
        String generate = chatLanguageModelOpenAi.generate("你好，你是谁");
        System.out.println(generate);
    }

    @Test
    public void testChatMessage(){
        UserMessage userMessage = UserMessage.from("你好，你是谁");
        Response<AiMessage> response = chatLanguageModel.generate(userMessage);
        System.out.println(response);
    }

    @Test
    public void testChatAssistant(){
        String response = chatAssistant.chat("你好，你是谁");
        System.out.println(response);
    }
}
