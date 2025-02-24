package cn.baruto.langchain4j;

import cn.baruto.langchain4j.prompt.LegalPrompt;
import dev.langchain4j.model.input.PromptTemplate;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(classes = PromptApplication.class)
public class PromptApplicationTest {

    @Resource
    private ChatAssistant chatAssistant;

    @Test
    public void test1(){
        System.out.println(chatAssistant.chat1("劳动法中，什么是劳动者的权益？"));
    }

    @Test
    public void test2(){
        LegalPrompt legalPrompt = new LegalPrompt();
        legalPrompt.setLegal("劳动法");
        legalPrompt.setQuestion("什么是劳动者的权益？");
        System.out.println(chatAssistant.chat2(legalPrompt));
    }

    @Test
    public void test3(){
        PromptTemplate template1 = PromptTemplate.from("请解释中国法律中'{{it}}'概念");
        System.out.println(template1.apply("劳动法").text());

        PromptTemplate template2 = PromptTemplate.from("请解释中国法律中'{{legal}}'概念");
        System.out.println(template2.apply(Map.of("legal","劳动法")).text());
    }
}
