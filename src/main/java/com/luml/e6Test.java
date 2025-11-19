package com.luml;

/**
 * @author luml
 * @description
 * @date 2025/11/18
 */
public class e6Test {

    public void testscore(){
        //日常考核
        //1、录入时动态计算
        /**
         * 录入完成要保存三张表
         * 录入时记录到cockpit_assessment_indicator指标数据表里面 分数和次数
         *      `dimension_id` int DEFAULT NULL COMMENT '外键',
         *      `indicator_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '指标名称',
         *      `indicator_score` int DEFAULT NULL COMMENT '指标分数',
         *      `counts` int DEFAULT '0' COMMENT '次数(考核时填报)',
         *   页面动态计算 维度得分
         * cockpit_assessment_dimension：考核维度数据表
         *      `assessment_id` int DEFAULT NULL COMMENT '考核ID',
         *      `dimension_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '维度名称',
         *      `total_score` int DEFAULT NULL COMMENT '维度总分数',
         *              固定的吧 15分
         *      `score` int DEFAULT NULL COMMENT '维度得分',
         *              计算出来 扣掉：次数*分值/次（模板里规定的）；即0次--0、1次--1、2次--2、3次--3；
         *                     得分=分值-（0*分值次）
         *                     15(是维度的分数) - （非0次的 次数1 *  cockpit_indicator.indicator_score + N ）
         *      `calc_symbol` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计算符号+ - 0 0表示一票否决',
         * cockpit_assessment：考核数据表
         *      总分计算
         *          total分数=0
         *          for(维度 维度A : 维度list){
         *
         *              维度分数 a = 0;
         *              for(指标 指标A : 指标list){
         *                  a += 指标a分数*次数
         *              }
         *              total分数 += 维度分数；
         *          }
         *      总分：
         *      总分--->等级 用下面的方法
         *             若有一票否决 评级直接D
         *      得加一个 级别和总分数两个字段
         */
        //月度考核 -定期复核
        //综合评价

    }


}
