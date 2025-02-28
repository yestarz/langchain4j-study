package cn.baruto.langchain4j;

import cn.baruto.langchain4j.service.AiAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = McpApplication.class)
public class McpApplicationTest {

    @Resource
    private AiAssistant aiAssistant;

    @Test
    public void test1(){
        System.out.println(aiAssistant.chat("给我列出知乎热榜前10？"));
    }

    @Test
    public void test2(){
        System.out.println(aiAssistant.chat("给我列出抖音热榜前10？"));
    }

    @Test
    public void testWeather1(){
        System.out.println(aiAssistant.chat("今天重庆的天气怎么样？"));
    }
}
