package cn.baruto.langchain4j.config;

import cn.baruto.langchain4j.service.AiAssistant;
import com.mysql.cj.jdbc.MysqlDataSource;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.experimental.rag.content.retriever.sql.SqlDatabaseContentRetriever;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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

    /**
     * 1、创建Qdrant客户端
     *
     * @return
     */
    @Bean
    public QdrantClient qdrantClient() {
        QdrantGrpcClient.Builder grpcClientBuilder =
                QdrantGrpcClient.newBuilder("192.168.56.106", 6334, false);
        return new QdrantClient(grpcClientBuilder.build());
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return QdrantEmbeddingStore.builder()
                .host("192.168.56.106")
                .port(6334)
                .collectionName("rag1")
                .build();
    }

    /**
     * @return
     */
    @Bean
    public AiAssistant aiAssistant() throws ClassNotFoundException {
        /*ContentRetriever sqlDatabaseContentRetriever = SqlDatabaseContentRetriever.builder()
                .dataSource(dataSource())
                .chatLanguageModel(chatLanguageModel())
                .build();*/
        return AiServices.builder(AiAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                //.contentRetriever(sqlDatabaseContentRetriever)
                .build();
    }

    //@Bean
    public DataSource dataSource() throws ClassNotFoundException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=false".formatted(System.getenv("MYSQL_HOST"),Integer.parseInt(System.getenv("MYSQL_PORT")), System.getenv("MYSQL_DATABASE")));
/*        dataSource.setServerName(System.getenv("MYSQL_HOST"));
        dataSource.setPortNumber(Integer.parseInt(System.getenv("MYSQL_PORT")));
        dataSource.setDatabaseName(System.getenv("MYSQL_DATABASE"));*/
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));
        return dataSource;
    }

}
