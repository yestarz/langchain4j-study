package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.ChatAssistant;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.Arrays;

@Configuration
public class LLMConfig {

    @Resource
    private TestChatModelListener testChatModelListener;

    @Resource
    private TestChatModelListener2 testChatModelListener2;

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
                .listeners(Arrays.asList(testChatModelListener2,testChatModelListener)) // 多个监听器，按照顺序执行
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    /**
     * openai的模型
     *
     * @return
     */
    @Bean
    public ChatLanguageModel chatLanguageModelOpenAi() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.valueOf(System.getProperty("http.proxyPort"))));
        return OpenAiChatModel.builder()
                .proxy(proxy)
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .maxRetries(5) // 重试，处理失败时重试的次数
                .timeout(Duration.ofSeconds(10)) // 超时配置
                .baseUrl("https://api.openai.com/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant() {
        return AiServices.builder(ChatAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .build();
    }
}
