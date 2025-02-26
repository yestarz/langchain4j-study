package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.service.AiAssistant;
import cn.hutool.core.io.FileUtil;
import com.mysql.cj.jdbc.MysqlDataSource;
import dev.langchain4j.experimental.rag.content.retriever.sql.SqlDatabaseContentRetriever;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
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

    @Bean
    public AiAssistant aiAssistant(ChatLanguageModel chatLanguageModel,DataSource dataSource) throws ClassNotFoundException {
        String sqlFile = "C:\\Users\\lucky\\Desktop\\bella_growth.sql";
        String sql = FileUtil.readString(sqlFile, "UTF-8");

        ContentRetriever sqlDatabaseContentRetriever = SqlDatabaseContentRetriever.builder()
                .dataSource(dataSource)
                .chatLanguageModel(chatLanguageModel)
                .databaseStructure(sql)
                .build();
        return AiServices.builder(AiAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .contentRetriever(sqlDatabaseContentRetriever)
                .build();
    }

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=false".formatted(System.getenv("MYSQL_HOST"), Integer.parseInt(System.getenv("MYSQL_PORT")), System.getenv("MYSQL_DATABASE")));
/*        dataSource.setServerName(System.getenv("MYSQL_HOST"));
        dataSource.setPortNumber(Integer.parseInt(System.getenv("MYSQL_PORT")));
        dataSource.setDatabaseName(System.getenv("MYSQL_DATABASE"));*/
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));
        return dataSource;
    }

}
