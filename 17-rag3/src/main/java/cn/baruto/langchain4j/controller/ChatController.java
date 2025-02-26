package cn.baruto.langchain4j.controller;

import cn.baruto.langchain4j.service.AiAssistant;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Resource
    private AiAssistant aiAssistant;

    @RequestMapping("/chat")
    public Flux<String> chat(String input, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return aiAssistant.chat(input);
    }
}
