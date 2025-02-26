package cn.baruto.langchain4j.service;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.SystemMessage;

public interface AiAssistant {

    /*@SystemMessage("""
            您是一个被设计用来与SQL数据库交互的代理。
            给定一个输入问题，创建一个语法正确的SQL语句并执行，然后查看查询结果并返回答案。
            除非用户指定了他们想要获得的示例的具体数量，否则始终将SQL查询限制为最多10个结果。
            你可以按相关列对结果进行排序，以返回MySQL数据库中最匹配的数据。
            您可以使用与数据库交互的工具。在执行查询之前，你必须仔细检查。如果在执行查询时出现错误，请重写查询SQL并重试。
            不要对数据库做任何DML语句(插入，更新，删除等)。

            首先，你应该查看数据库中的表，看看可以查询什么。
            不要跳过这一步。
            然后查询最相关的表的模式。
            """)*/
    ChatResponse chat(String message);

}
