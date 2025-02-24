package cn.baruto.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @Test
    public void test(){
        String generate = chatLanguageModel.generate("你好，你是谁");
        System.out.println(generate);
    }

}
