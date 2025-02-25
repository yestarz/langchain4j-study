package cn.baruto.langchain4j;



import cn.baruto.langchain4j.service.FunctionAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FunctionApplication.class)
public class FunctionApplicationTest {

    @Resource
    private FunctionAssistant functionAssistant1;

    @Resource
    private FunctionAssistant functionAssistant2;

    @Test
    public void test1(){
        System.out.println(functionAssistant1.chat("帮我开局发票，公司是苹果公司，税号：85VF34，金额：一千零一十二"));
    }

    @Test
    public void test2(){
        System.out.println(functionAssistant2.chat("帮我开局发票，公司是苹果公司，税号：85VF34，金额：一千零一十五"));
    }
}
