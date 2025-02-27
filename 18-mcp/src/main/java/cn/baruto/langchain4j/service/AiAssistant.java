package cn.baruto.langchain4j.service;

import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

public interface AiAssistant {

    String chat(String message);

}
