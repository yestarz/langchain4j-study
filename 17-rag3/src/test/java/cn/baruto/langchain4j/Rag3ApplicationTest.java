package cn.baruto.langchain4j;

import cn.baruto.langchain4j.service.AiAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Rag3Application.class)
public class Rag3ApplicationTest {

    @Resource
    private AiAssistant aiAssistant;

    @Test
    public void test1(){
        System.out.println(aiAssistant.chat("请问用户表中有多少条数据？"));
    }
}
