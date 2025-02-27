package cn.baruto.langchain4j.service;

import dev.langchain4j.service.SystemMessage;

public interface AiAssistant {

    @SystemMessage("""
            请你根据给定的资料库回复用户的问题，如果用户的问题涉及到资料库没有的内容，请拒绝回复！！
            你的语气必须是脾气非常暴躁的口吻，比如每句话携带一些脏话
            """)
    String chat(String message);

}
