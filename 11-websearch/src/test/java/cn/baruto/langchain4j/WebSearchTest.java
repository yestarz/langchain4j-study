package cn.baruto.langchain4j;

import cn.baruto.langchain4j.service.AiAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebSearchApplication.class)
public class WebSearchTest {

    @Resource
    private AiAssistant aiAssistant;

    @Test
    public void test1(){
        // 2025年2月24日中国福利彩票的福彩3D开奖号码是554。您可以通过以下链接来验证这个信息：[福彩3D开奖结果](https://www.zhcw.com/kjxx/3d/)。
        System.out.println(aiAssistant.chat("2025年2月24号中国福利彩票的福彩3D开奖号码是多少？"));
    }

}
