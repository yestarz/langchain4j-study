# Chat and Language Models (聊天和语言模型)

这个`ChatLanguageModel`是一个低级的LLM API
这些请求接受多个 ChatMessage作为输入，并返回单个 ChatResponse 作为输出。 ChatMessage 通常包含文本，但有些LLMs还支持其他形式（例如，图像、音频等）

除了ChatLanguageModel ，LangChain4j 还支持以下类型的模型：

EmbeddingModel - 此模型可以将文本转换为 Embedding。
ImageModel - 此模型可以生成和编辑图像。
ModerationModel - 此模型可以检查文本是否包含有害内容。
ScoringModel - 此模型可以根据查询对多段文本进行评分（或排名），实质上确定每段文本与查询的相关性。这对 RAG 很有用。

构建ChatLanguageModel:

```java
    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("AI_DASHSCOPE_API_KEY"))
                .modelName("qwen-turbo")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }
```

# 消息类型

## UserMessage

这是来自用户的消息。用户可以是应用程序的最终用户 （人类） 或应用程序本身。根据 支持的形式，LLMUserMessage 可以只包含文本 （String） 或其他形式。

## AiMessage

这是由 AI 生成的消息，通常是为了响应 UserMessage。 AiMessage 可以包含文本响应 （String） 或执行工具的请求 （ToolExecutionRequest）

## ToolExecutionResultMessage

这是 ToolExecutionRequest 的结果

## SystemMessage

这是来自系统的消息。通常，作为开发人员，您应该定义此消息的内容。通常，您会在此处编写有关LLM在此对话中的角色、它应该如何行为、以何种方式回答等的说明。LLMs经过训练，它们会比其他类型的消息更关注 SystemMessage，因此请小心，最好不要让最终用户免费访问来定义或注入一些 Input。通常，它位于对话的开头。

## CustomMessage

这是可以包含任意属性的自定义消息。此消息类型只能由 ChatLanguageModel 实现（目前只有 Ollama）。

----

# UserMessage

UserMessage 不仅可以包含文本，还可以包含其他类型的内容。 UserMessage 包含 List<Content> 内容。 Content 是一个接口，具有以下实现：

TextContent  文本内容
ImageContent  图像内容
AudioContent  音频内容
VideoContent  视频内容
PdfFileContent  PdfFileContent （英文）
