package cn.baruto.langchain4j.service;

public interface FunctionAssistant {

    /**
     * 用户提问：帮我开局发票，开票信息是xxx，税号：xxx，金额：xxx
     *
     * @param message
     * @return
     */
    String chat(String message);

}
