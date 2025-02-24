package cn.baruto.langchain4j;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 * 类似于OpenFeign的调用形式
 */
public interface ChatAssistant {

    String chat(String message);

    String chat(@MemoryId Long userId, @UserMessage String message);
}
