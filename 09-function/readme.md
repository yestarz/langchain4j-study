# 提示词使用的3钟方式

## 使用@UserMessage和@V注解
```java
    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    @UserMessage("请回答以下法律问题：{{question}}")
    String chat1(@V("question") String question);
```

## 使用实体类来构造

```java
@Data
@StructuredPrompt("根据中国{{legal}}法律，解答以下问题：{{question}}")
public class LegalPrompt {

    private String legal;
    private String question;

}

```

```java
    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    String chat2(LegalPrompt prompt);

```

## 使用PromptTemplate
```java
    @Test
    public void test3(){
        PromptTemplate template1 = PromptTemplate.from("请解释中国法律中'{{it}}'概念");
        System.out.println(template1.apply("劳动法").text());

        PromptTemplate template2 = PromptTemplate.from("请解释中国法律中'{{legal}}'概念");
        System.out.println(template2.apply(Map.of("legal","劳动法")).text());
    }
```
