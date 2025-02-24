package cn.baruto.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.service.TokenStream;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ChatStreamApplication.class)
public class ChatStreamApplicationTest {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @Resource
    private StreamingChatLanguageModel streamingChatLanguageModel;

    @Resource
    private ChatAssistant chatAssistant;

    @Test
    public void test1() {
        String generate = chatLanguageModel.generate("你好，你是谁");
        System.out.println(generate);
    }

    @Test
    public void test2() {
        streamingChatLanguageModel.generate("重庆有什么好吃的？", new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                System.out.printf("%s", token);
                //System.out.println(token);
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test3(){
        TokenStream tokenStream = chatAssistant.stream("乌鲁木齐有什么好吃的？");
        tokenStream.onNext(System.out::print)
                .onComplete((message) -> {
                    System.out.println("On Complete ---->");
                    System.out.println(message);
                })
                .onError(Throwable::printStackTrace)
                .start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
