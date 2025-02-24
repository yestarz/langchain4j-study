package cn.baruto.langchain4j;

/**
 * 类似于OpenFeign的调用形式
 */
public interface ChatAssistant {

    String chat(String userMessage);

}
