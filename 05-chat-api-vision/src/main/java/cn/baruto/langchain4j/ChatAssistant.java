package cn.baruto.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.TokenStream;
import reactor.core.publisher.Flux;

/**
 * 类似于OpenFeign的调用形式
 */
public interface ChatAssistant {

   String chat(UserMessage userMessage);

}
