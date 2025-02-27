package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.service.AiAssistant;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    /**
     * @return
     */
    @Bean
    public AiAssistant aiAssistant(McpClient mcpClient) throws ClassNotFoundException {
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();
        return AiServices.builder(AiAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .toolProvider(toolProvider)
                //.contentRetriever(sqlDatabaseContentRetriever)
                .build();
    }

    @Bean
    public McpTransport mcpTransport() {
        return new StdioMcpTransport.Builder()
                .command(List.of("D:\\Program Files\\nodejs\\npx.cmd", "-y", "@wopal/mcp-server-hotnews"))
                .logEvents(true) // only if you want to see the traffic in the log

                .build();
    }

    @Bean
    public McpClient mcpClint(McpTransport mcpTransport){
        return new DefaultMcpClient.Builder()
                .transport(mcpTransport)
                .build();
    }

}
