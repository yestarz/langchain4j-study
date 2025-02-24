package cn.baruto.langchain4j;

import cn.hutool.core.codec.Base64;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

@SpringBootTest(classes = ChatApiVisionApplication.class)
public class ChatStreamApplicationTest {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @Resource
    private ChatAssistant chatAssistant;

    @Test
    public void test1() throws URISyntaxException, IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("1.png");
        UserMessage userMessage = UserMessage.from(TextContent.from("从以下图片中获取9月30号的上证指数是多少？"),
                ImageContent.from(Base64.encode(inputStream), "image/png"));

        Response<AiMessage> response = chatLanguageModel.generate(userMessage);
        System.out.println(response.content().text());

    }

    @Test
    public void test2() throws URISyntaxException, IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("1.png");
        UserMessage userMessage = UserMessage.from(TextContent.from("从以下图片中获取9月27号的上证指数是多少？"),
                ImageContent.from(Base64.encode(inputStream), "image/png"));

        String response = chatAssistant.chat(userMessage);
        System.out.println(response);

    }

}
