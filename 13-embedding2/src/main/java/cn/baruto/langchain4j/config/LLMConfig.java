package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.enums.PersonalityTrait;
import dev.langchain4j.classification.EmbeddingModelTextClassifier;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    /**
     * 初始化向量模型的bean
     *
     * @return
     */
    @Bean
    public EmbeddingModel embeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("AI_DASHSCOPE_API_KEY"))
                .modelName("text-embedding-v3")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public EmbeddingModelTextClassifier<PersonalityTrait> textClassifier(EmbeddingModel embeddingModel) {
        return new EmbeddingModelTextClassifier<>(embeddingModel, PersonalityTrait.PersonalityTraitExamples.examples);
    }

}
