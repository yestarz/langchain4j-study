```java
/**
 * 这个类负责将文档摄入嵌入存储。
 * 它使用各种组件来转换、分割和嵌入文档，然后存储它们。
 */
public class EmbeddingStoreIngestor {

    // 将输入文档转换为适合处理的格式
    private final DocumentTransformer documentTransformer;

    // 将文档分割成更小的段落
    private final DocumentSplitter documentSplitter;

    // 转换单个文本段落（例如，用于标准化或清理）
    private final TextSegmentTransformer textSegmentTransformer;

    // 为文本段落生成嵌入向量
    private final EmbeddingModel embeddingModel;

    // 存储生成的嵌入向量及其对应的文本段落
    private final EmbeddingStore<TextSegment> embeddingStore;
}
```
```java
// 使用构建器模式的示例
EmbeddingStoreIngestor
    .builder()
    .documentTransformer()  // 设置文档转换器
    .documentSplitter()     // 设置文档分割器
    .embeddingModel()       // 设置嵌入模型
    .embeddingStore()       // 设置嵌入存储
    .build()                // 构建EmbeddingStoreIngestor实例
    .ingest(document);      // 将文档摄入嵌入存储
```
