package cn.baruto.langchain4j;

import cn.baruto.langchain4j.service.AiAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseToolApplicationTest {

    @Resource
    private AiAssistant aiAssistant;

    @BeforeAll
    public static void setUp() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "7890");
    }

    @Test
    public void test1() {
        System.out.println(aiAssistant.chat("用户表有多少条记录？"));
    }



}
