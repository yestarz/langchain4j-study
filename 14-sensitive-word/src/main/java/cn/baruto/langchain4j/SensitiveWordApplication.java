package cn.baruto.langchain4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SensitiveWordApplication {

    public static void main(String[] args) {
        System.setProperty("http.proxyHost","127.0.0.1");
        System.setProperty("http.proxyPort","7890");
        SpringApplication.run(SensitiveWordApplication.class, args);
    }

}
