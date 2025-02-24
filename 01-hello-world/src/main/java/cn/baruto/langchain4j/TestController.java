package cn.baruto.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @GetMapping("/chat")
    public String chat(String input){
        return chatLanguageModel.generate(input);
    }
}
