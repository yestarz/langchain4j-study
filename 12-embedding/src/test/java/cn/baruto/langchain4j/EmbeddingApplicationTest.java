package cn.baruto.langchain4j;

import cn.baruto.langchain4j.service.AiAssistant;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

@SpringBootTest(classes = EmbeddingApplication.class)
public class EmbeddingApplicationTest {

    @Resource
    private AiAssistant aiAssistant;

    @Resource
    private EmbeddingModel embeddingModel;

    @Resource
    private QdrantClient qdrantClient;

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Test
    public void test1() {
        Response<Embedding> response = embeddingModel.embed("测试文本，hello world！");
        System.out.println(response);

    }

    @Test
    public void createCollection() {
        var vectorParams = Collections.VectorParams.newBuilder()
                .setDistance(Collections.Distance.Cosine)
                .setSize(1024) // 这里表示的是维度，不同厂商的维度可能不同，阿里云的是1024，openai的是1564，所以不同的厂商的向量数据不兼容
                .build();
        qdrantClient.createCollectionAsync("testv", vectorParams);
    }

    @Test
    public void testEmbedding1() {
        TextSegment segment1 = TextSegment.from("浏览器报错 404，请检测您输入的路径是否正确");
        segment1.metadata().put("author", "xinkong");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);
    }

    @Test
    public void testEmbeddingQuery1() {
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);
        System.out.println(searchResult.matches().get(0).embedded().text());
    }


    /**
     * 根据metadata过滤
     */
    @Test
    public void testEmbeddingQuery2() {
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .filter(metadataKey("author").isEqualTo("xinkong2"))
                .maxResults(1)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);
        System.out.println(searchResult.matches().get(0).embedded().text());
    }
}
