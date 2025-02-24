package cn.baruto.langchain4j.config;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestChatModelListener2 implements ChatModelListener {
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        ChatModelListener.super.onRequest(requestContext);
        requestContext.attributes().put("test2", "TestChatModelListener2");
        log.info("==== TestChatModelListener2[onRequest] ==== 参数1： 【{}】 , 参数2：【{}】", requestContext.attributes().get("test1"), requestContext.attributes().get("test1"));
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        log.info("==== TestChatModelListener2[onResponse] ==== 参数1： 【{}】 , 参数2：【{}】", responseContext.attributes().get("test1"), responseContext.attributes().get("test2"));
        ChatModelListener.super.onResponse(responseContext);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.info("==== TestChatModelListener2[onResponse] ===== 参数1： 【{}】 , 参数2：【{}】", errorContext.attributes().get("test1"), errorContext.attributes().get("test2"));
        ChatModelListener.super.onError(errorContext);
    }
}
