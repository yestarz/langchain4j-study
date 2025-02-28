package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.service.AiAssistant;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolSpecification;
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
    public AiAssistant aiAssistant(McpClient mcpClient1, McpClient mcpClient2) throws ClassNotFoundException {
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient2, mcpClient1
                        //,mcpClient2
                ))
                .build();
        return AiServices.builder(AiAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .toolProvider(toolProvider)
                //.contentRetriever(sqlDatabaseContentRetriever)
                .build();
    }

    @Bean
    public McpClient mcpClient1() {
        return new DefaultMcpClient.Builder()
                .transport(new StdioMcpTransport.Builder()
                        .command(List.of("D:\\Program Files\\nodejs\\npx.cmd", "-y", "@wopal/mcp-server-hotnews"))
                        .logEvents(true) // only if you want to see the traffic in the log
                        .build())
                .build();
    }

    @Bean
    public McpClient mcpClient2() {
        return new DefaultMcpClient.Builder()
                .transport(new StdioMcpTransport.Builder()
                        .command(List.of("C:\\Users\\lucky\\.jdks\\graalvm-jdk-17.0.11\\bin\\java.exe", "-Dspring.ai.mcp.server.stdio=true", "-jar", "D:\\code\\mcp-server-simple\\target\\mcp-server-weather-0.0.1-SNAPSHOT.jar", "--weather.api.api-key=%s".formatted(System.getenv("HEFENG_WEATHER_API_KEY"))))
                        .logEvents(true) // only if you want to see the traffic in the log
                        .build())
                .build();
    }

}
