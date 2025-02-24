package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.ChatAssistant;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {


    /**
     * 阿里云的模型
     *
     * @return
     */
    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("AI_DASHSCOPE_API_KEY"))
                .modelName("qwen-turbo")
                .logRequests(true)
                .logResponses(true)
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant() {
        return AiServices.builder(ChatAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                //.chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }
}
