package cn.baruto.langchain4j.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

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
                .baseUrl("https://api.openai.com/v1")
                .build();
    }

}
