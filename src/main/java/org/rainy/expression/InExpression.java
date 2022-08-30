package org.rainy.expression;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @description: in表达式解析器
 * @author: zhangyu
 * @date: 2021/9/29 0029 15:09
 */
public class InExpression implements Expression {

    private final String key;
    private final String[] ruleValues;

    public InExpression(String key, String[] ruleValues) {
        this.key = key;
        this.ruleValues = ruleValues;
    }

    public InExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【in】表达式不能为空");
        String[] elements = ruleExpression.split("\\s+");
        if (elements.length != 3 || !Objects.equals(elements[1], ExpressionEnum.IN.getSeparator())) {
            throw new ExpressionException("【in】表达式格式错误");
        }
        if (elements[0].contains(",")) {
            this.key = elements[2];
            this.ruleValues = elements[0].split(",");
        } else {
            this.key = elements[0];
            this.ruleValues = elements[2].split(",");
        }
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        if (!stats.containsKey(this.key)) {
            return false;
        }
        return Arrays.stream(ruleValues).anyMatch(ruleValue -> Objects.equals(stats.get(this.key).trim(), toEmpty(ruleValue)));
    }

}
