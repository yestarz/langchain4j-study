package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.function.InvoiceHandler;
import cn.baruto.langchain4j.service.FunctionAssistant;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static dev.langchain4j.agent.tool.JsonSchemaProperty.description;
import static dev.langchain4j.agent.tool.JsonSchemaProperty.type;

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
     * 通过工具说明和业务逻辑来注入工具
     *
     * @param chatLanguageModel
     * @return
     */
    @Bean
    public FunctionAssistant functionAssistant1(ChatLanguageModel chatLanguageModel) {
        // 工具说明 ToolSpecification
        ToolSpecification toolSpecification = ToolSpecification.builder()
                .name("invoice_assistant")
                .description("根据用户提交的开票信息，开具发票")
                .addParameter("companyName", type("string"), description("公司名称"))
                .addParameter("dutyNumber", type("string"), description("税号"))
                .addParameter("amount", type("string"), description("金额，保留2位有效数字"))
                .build();

        // 业务逻辑 ToolExecutor
        ToolExecutor toolExecutor = (toolExecutionRequest, memoryId) -> {
            String arguments1 = toolExecutionRequest.arguments();
            System.out.println("arguments1 =>>>> " + arguments1);
            return "开具成功";
        };

        return AiServices.builder(FunctionAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .tools(Map.of(toolSpecification, toolExecutor))
                .build();
    }

    /**
     * 通过注解来注入工具信息
     *
     * @param chatLanguageModel
     * @return
     */
    @Bean
    public FunctionAssistant functionAssistant2(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(FunctionAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .tools(new InvoiceHandler())
                .build();
    }

}
