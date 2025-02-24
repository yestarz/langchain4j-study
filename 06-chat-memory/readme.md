
## MessageWindowChatMemory（简单实现）
> 这是一个基于消息数量的简单实现。它采用滑动窗口的方式,保留最新的N条消息,并淘汰旧消息。

特点:

易于理解和实现
基于消息数量进行管理
适用于不需要精确token控制的场景

## TokenWindowChatMemory
> 这是一个更复杂的实现,它同样采用滑动窗口的方式,但侧重于保留最新的tokens,而不是消息数量。
> 
```java
ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
```

特点:

更精确的token控制
需要结合Tokenizer计算ChatMessage的token数量
适用于需要严格控制token使用的场景

> 注意: 使用TokenWindowChatMemory需要提供一个Tokenizer实例,用于计算消息的token数量。不同的语言模型可能需要不同的Tokenizer。

```java
Tokenizer tokenizer = new OpenAiTokenizer(GPT_3_5_TURBO);
ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(1000, tokenizer);
```
# 自定义持久化

实现 `ChatMemoryStore`接口

```java
public class PersistentChatMemoryStore implements ChatMemoryStore {
    private final DB db = DBMaker.fileDB("./chat-memory.db").transactionEnable().make();
    private final Map<Integer, String> map = db.hashMap("messages", INTEGER, STRING).createOrOpen();

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String json = map.get((int) memoryId);
        return messagesFromJson(json);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        String json = messagesToJson(messages);
        map.put((int) memoryId, json);
        db.commit();
    }

    @Override
    public void deleteMessages(Object memoryId) {
        map.remove((int) memoryId);
        db.commit();
    }
}
```

# 注意事项
SystemMessage会被特殊处理,始终保留且只允许一个
工具消息(如函数调用)需要成对处理
默认实现是基于内存的,如需持久化需自行实现
