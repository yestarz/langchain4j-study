package cn.baruto.langchain4j.service;

import dev.langchain4j.service.SystemMessage;

public interface AiAssistant {

    String chat(String message);

}
