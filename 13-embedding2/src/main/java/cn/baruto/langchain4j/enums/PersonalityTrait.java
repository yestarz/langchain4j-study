package cn.baruto.langchain4j.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum PersonalityTrait {
    /**
     * 外向型：喜欢社交，从与他人互动中获得能量
     */
    EXTROVERT,

    /**
     * 内向型：倾向于独处，需要安静时间来恢复能量
     */
    INTROVERT,

    /**
     * 分析型：擅长逻辑思考，喜欢解决复杂问题
     */
    ANALYTICAL,

    /**
     * 创意型：富有想象力，常有新颖的想法
     */
    CREATIVE,

    /**
     * 领导型：善于指导他人，乐于承担责任
     */
    LEADER,

    /**
     * 团队合作型：重视协作，善于在团队中工作
     */
    TEAM_PLAYER;

    public static class PersonalityTraitExamples {
        public static final Map<PersonalityTrait, List<String>> examples = Map.of(
                PersonalityTrait.EXTROVERT, List.of(
                        "我喜欢结识新朋友",
                        "团体活动让我充满活力",
                        "我经常是聚会的焦点",
                        "我喜欢在热闹的社交环境中工作"
                ),
                PersonalityTrait.INTROVERT, List.of(
                        "我更喜欢独自工作",
                        "我需要安静的时间来充电",
                        "大型社交聚会让我感到压抑",
                        "我喜欢深入的一对一谈话"
                ),
                PersonalityTrait.ANALYTICAL, List.of(
                        "我喜欢解决复杂的问题",
                        "数据驱动的决策至关重要",
                        "我总是寻找信息中的模式和联系",
                        "我倾向于在采取行动之前彻底分析情况"
                ),
                PersonalityTrait.CREATIVE, List.of(
                        "我常常跳出框架思考",
                        "我总是想出新的点子",
                        "我喜欢寻找创新的解决方案",
                        "我受到周围艺术和美的启发"
                ),
                PersonalityTrait.LEADER, List.of(
                        "我能自信地领导项目",
                        "我激励他人实现目标",
                        "我喜欢指导和培养团队成员",
                        "我不怕做出艰难的决定"
                ),
                PersonalityTrait.TEAM_PLAYER, List.of(
                        "合作是成功的关键",
                        "我重视所有团队成员的意见",
                        "我总是愿意帮助我的同事",
                        "我相信团队中多样化视角的力量"
                )
        );
    }

}
