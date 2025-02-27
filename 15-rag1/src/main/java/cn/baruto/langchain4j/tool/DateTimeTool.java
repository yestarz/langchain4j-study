package cn.baruto.langchain4j.tool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import dev.langchain4j.agent.tool.Tool;

import java.util.Date;

public class DateTimeTool {

    @Tool("用于计算当前时间")
    String now(){
        return DateUtil.formatDateTime(new Date());
    }

    @Tool("用于计算当前星期几")
    Week getWeekday(){
        return DateUtil.dayOfWeekEnum(new Date());
    }
}
