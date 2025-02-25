package cn.baruto.langchain4j;

import cn.baruto.langchain4j.enums.PersonalityTrait;
import dev.langchain4j.classification.EmbeddingModelTextClassifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = Embedding2Application.class)
public class Embedding2ApplicationTest {

    @Autowired
    private EmbeddingModelTextClassifier<PersonalityTrait> textClassifier;

    @Test
    void analyzePersonality() {
        List<PersonalityTrait> classify1 = textClassifier.classify("赠人玫瑰，手有余香");
        System.out.println(classify1);

        List<PersonalityTrait> classify2 = textClassifier.classify("我喜欢新朋友");
        System.out.println(classify2);
    }
}
