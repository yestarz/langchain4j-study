package cn.baruto.langchain4j;

import dev.langchain4j.service.TokenStream;
import reactor.core.publisher.Flux;

/**
 * 类似于OpenFeign的调用形式
 */
public interface ChatAssistant {

    String chat(String userMessage);

    TokenStream stream(String userMessage);

    Flux<String> streamFlux(String userMessage);
}
