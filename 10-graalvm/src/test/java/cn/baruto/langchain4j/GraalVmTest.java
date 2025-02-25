package cn.baruto.langchain4j;

import dev.langchain4j.code.CodeExecutionEngine;
import dev.langchain4j.code.graalvm.GraalVmJavaScriptExecutionEngine;
import dev.langchain4j.code.graalvm.GraalVmPythonExecutionEngine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

public class GraalVmTest {

    /**
     * 如果执行失败，需要安装js模块
     * gu install js  # 安装js
     * gu list # 查看安装的列表
     */
    @Test
    public void test1(){
        CodeExecutionEngine engine = new GraalVmJavaScriptExecutionEngine();

        String code = """
        function fibonacci(n) {
            if (n <= 1) return n;
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
                        
        fibonacci(10)
        """;

        String result = engine.execute(code);
        System.out.println(result);
    }

}
