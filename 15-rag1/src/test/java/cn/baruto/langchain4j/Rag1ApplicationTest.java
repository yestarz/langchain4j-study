package cn.baruto.langchain4j;

import cn.baruto.langchain4j.service.AiAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@SpringBootTest(classes = Rag1Application.class)
public class Rag1ApplicationTest {

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Resource
    private QdrantClient qdrantClient;

    @Resource
    private EmbeddingModel embeddingModel;

    @Resource
    private AiAssistant aiAssistant;

    @Test
    public void add() throws URISyntaxException {
        URL resourceUrl = this.getClass().getClassLoader().getResource("test1.txt");
        File file = new File(resourceUrl.toURI());
        String filePath = file.getAbsolutePath();
        System.out.println(filePath);

        Document document = FileSystemDocumentLoader.loadDocument(filePath);
        EmbeddingStoreIngestor
                .builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build()
                .ingest(document);
    }

    @Test
    public void createCollection() {
        var vectorParams = Collections.VectorParams.newBuilder()
                .setDistance(Collections.Distance.Cosine)
                .setSize(1024) // 这里表示的是维度，不同厂商的维度可能不同，阿里云的是1024，openai的是1564，所以不同的厂商的向量数据不兼容
                .build();
        qdrantClient.createCollectionAsync("rag1", vectorParams);
    }

    // ------------------------------------------------------------

    @Test
    public void test1(){
        System.out.println(aiAssistant.chat("张三这个员工的月工资是多少？"));
    }

    @Test
    public void test2(){
        System.out.println(aiAssistant.chat("工作时间是？"));
    }

    @Test
    public void test3(){
        System.out.println(aiAssistant.chat("有几天年假？"));
    }
}
