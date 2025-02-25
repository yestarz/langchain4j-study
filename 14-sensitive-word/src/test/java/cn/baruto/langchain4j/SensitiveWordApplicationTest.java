package cn.baruto.langchain4j;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SensitiveWordApplication.class)
public class SensitiveWordApplicationTest {

    @Test
    public void test1() {
        String text = "你妈逼的";// 测试文本
        check(text);
    }

    @Test
    public void test2() {
        String text = "大傻逼";// 测试文本
        check2(text);
    }

    private void check(String text) {
        // 检查是否包含敏感词
        boolean contains = SensitiveWordHelper.contains(text);

        // 查找第一个敏感词
        String firstWord = SensitiveWordHelper.findFirst(text);

        // 查找所有敏感词
        List<String> allWords = SensitiveWordHelper.findAll(text);

        // 替换敏感词
        String replaced = SensitiveWordHelper.replace(text);

        // 输出结果
        System.out.println("是否包含敏感词: " + contains);
        System.out.println("第一个敏感词: " + firstWord);
        System.out.println("所有敏感词: " + allWords);
        System.out.println("替换后的文本: " + replaced);
    }

    private void check2(String text) {

        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                // 使用默认的敏感词词库（黑名单）
                .wordDeny(WordDenys.defaults())
                // 使用默认的白名单词库，白名单中的词不会被视为敏感词，即使它们在黑名单中
                .wordAllow(WordAllows.defaults())
                // 忽略大小写，例如："FuCk" 和 "fuck" 将被同等对待
                .ignoreCase(true)
                // 忽略全角和半角字符的区别，例如："ｆｕｃｋ" 和 "fuck" 将被同等对待
                .ignoreWidth(true)
                // 启用连续数字检测， 可用于检测电话号码、QQ号等
                .enableNumCheck(true)
                // 启用邮箱地址检测，可用于过滤包含邮箱地址的文本
                .enableEmailCheck(true)
                // 初始化敏感词过滤器， 这一步必须在所有配置完成后调用
                .init();

        // 使用配置好的过滤器检查文本是否包含敏感词

        // 检查是否包含敏感词
        boolean contains = wordBs.contains(text);

        // 查找第一个敏感词
        String firstWord = wordBs.findFirst(text);

        // 查找所有敏感词
        List<String> allWords = wordBs.findAll(text);

        // 替换敏感词
        String replaced = wordBs.replace(text);

        // 输出结果
        System.out.println("是否包含敏感词: " + contains);
        System.out.println("第一个敏感词: " + firstWord);
        System.out.println("所有敏感词: " + allWords);
        System.out.println("替换后的文本: " + replaced);
    }

}
