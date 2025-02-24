package cn.baruto.langchain4j;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestController {

    @Resource
    private ChatAssistant chatAssistant;

    @GetMapping("/stream")
    public Flux<String> streamFlux(String input, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return chatAssistant.streamFlux(input);
    }
}
