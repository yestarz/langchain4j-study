ChatApi的配置

# 日志配置

```java
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
```

同时设置输出的日志等级:

```yaml
logging:
  level:
    dev:
      ai4j:
        openai4j: debug
```
# 监控

实现`ChatModelListener`接口，可以配置多个，会依次调用
不同的监听器中可以共享参数，使用`responseContext.attributes()`设置和获取参数
