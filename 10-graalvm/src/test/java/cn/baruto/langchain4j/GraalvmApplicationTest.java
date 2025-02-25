package cn.baruto.langchain4j;

import cn.baruto.langchain4j.service.AiAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = GraalvmApplication.class)
public class GraalvmApplicationTest {

    @Resource
    private AiAssistant aiAssistant;

    /**
     * 大模型根据问题，生成js执行代码，然后执行代码，大模型再返回结果
     */
    @Test
    public void test1(){
        System.out.println(aiAssistant.chat("What is the square root of 485906798473894056 in scientific notation?"));
    }

}
