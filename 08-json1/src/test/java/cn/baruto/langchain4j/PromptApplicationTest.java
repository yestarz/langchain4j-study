package cn.baruto.langchain4j;


import cn.baruto.langchain4j.service.NumberExtractor;
import cn.baruto.langchain4j.service.PersonExtractor;
import cn.baruto.langchain4j.service.SentimentAnalyzer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Json1Application.class)
public class PromptApplicationTest {

    @Resource
    private NumberExtractor numberExtractor;

    @Resource
    private SentimentAnalyzer sentimentAnalyzer;

    @Resource
    private PersonExtractor personExtractor;

    @Test
    public void test1() {
        System.out.println(numberExtractor.extractInt("我赚了一百块"));
    }

    @Test
    public void test2() {
        //System.out.println(sentimentAnalyzer.isPositive("真他妈的服了，谁把我车给蹭了？"));
       // System.out.println(sentimentAnalyzer.isPositive("卧槽，我捡到了100块"));
        //System.out.println(sentimentAnalyzer.analyzeSentimentOf("真他妈的服了，谁把我车给蹭了？"));
        System.out.println(sentimentAnalyzer.analyzeSentimentOf("我开心，捡到了100块"));
    }

    @Test
    public void test3(){
        System.out.println(personExtractor.extractPerson("我的名字叫张三，出生于1994年2月3号"));
    }
}
