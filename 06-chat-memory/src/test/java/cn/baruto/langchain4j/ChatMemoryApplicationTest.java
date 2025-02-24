package cn.baruto.langchain4j;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest(classes = ChatMemoryApplication.class)
public class ChatMemoryApplicationTest {

    @Resource
    private ChatAssistant chatAssistant;

    @Test
    public void test(){
        System.out.println(chatAssistant.chat("你好，我的名字叫星空，接下来的聊天，请带上我的名字"));
        System.out.println(chatAssistant.chat("我叫什么名字？"));
    }

    @Test
    public void test1() {
        System.out.println(chatAssistant.chat(1L, "你好，我的名字叫星空，接下来的聊天，请带上我的名字"));
        System.out.println(chatAssistant.chat(1L, "我叫什么名字？"));

        System.out.println(chatAssistant.chat(2L, "你好，我的名字叫不不，接下来的聊天，请带上我的名字"));
        System.out.println(chatAssistant.chat(2L, "我叫什么名字？"));
    }


}
