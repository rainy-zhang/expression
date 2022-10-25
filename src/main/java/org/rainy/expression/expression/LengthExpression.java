package org.rainy.expression.expression;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @description: length表达式解析器
 * @author: zhangyu
 * @date: 2021/9/30 0030 9:46
 */
public class LengthExpression implements Expression {

    private final String realKey;
    private final Expression expression;

    public LengthExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【length】表达式不能为空");
        String[] elements = ruleExpression.split("\\s+");
        if (elements.length != 3
                || !elements[0].toLowerCase().contains(ExpressionEnum.LENGTH.getSeparator())
                || (!Objects.equals(elements[1], ExpressionEnum.GREATER.getSeparator())
                && !Objects.equals(elements[1], ExpressionEnum.GREATER_EQUALS.getSeparator())
                && !Objects.equals(elements[1], ExpressionEnum.LESS.getSeparator())
                && !Objects.equals(elements[1], ExpressionEnum.LESS_EQUALS.getSeparator())
                && !Objects.equals(elements[1], ExpressionEnum.NOT_EQUALS.getSeparator())
                && !Objects.equals(elements[1], ExpressionEnum.EQUALS.getSeparator()))) {
            throw new ExpressionException("【length】表达式格式错误");
        }

        // 取出真实的字段 -> name.length = name
        this.realKey = elements[0].split("\\.")[0];
        this.expression = ExpressionFactory.getExpression(realKey + " " + elements[1] + " " + elements[2]);
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        if (!stats.containsKey(this.realKey)) {
            return false;
        }
        Map<String, String> lengthStats = Collections.singletonMap(this.realKey, String.valueOf(stats.get(this.realKey).length()));
        return this.expression.interpret(lengthStats);
    }

}
