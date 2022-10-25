package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: zhangyu
 * @date: 2021/9/30 0030 15:25
 */
public class IfExpression implements Expression {

    private final Expression conditionExpression;
    private final Expression expression;

    public IfExpression(Expression conditionExpression, Expression expression) {
        this.conditionExpression = conditionExpression;
        this.expression = expression;
    }

    // if[state == 1]{time != ''}
    public IfExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【if】表达式不能为空");

        // 获取条件中的表达式
        final String conditionOpen = "[";
        final String conditionClose = "]";
        String conditionExpression = ruleExpression.substring(ruleExpression.indexOf(conditionOpen) + conditionOpen.length(), ruleExpression.indexOf(conditionClose));
        this.conditionExpression = ExpressionFactory.getExpression(conditionExpression);

        // 获取表达式
        final String expressionOpen = "{";
        final String expressionClose = "}";
        String realExpression = ruleExpression.substring(ruleExpression.indexOf(expressionOpen) + expressionOpen.length(), ruleExpression.indexOf(expressionClose));
        this.expression = ExpressionFactory.getExpression(realExpression);
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        if (conditionExpression.interpret(stats)) {
            return expression.interpret(stats);
        }
        return true;
    }
}
