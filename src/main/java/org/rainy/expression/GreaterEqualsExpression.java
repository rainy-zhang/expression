package org.rainy.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @description: >=表达式解析器
 * @author: zhangyu
 * @date: 2021/9/29 0029 15:41
 */
public class GreaterEqualsExpression implements Expression {

    private final GreaterExpression greaterExpression;
    private final EqualsExpression equalsExpression;

    public GreaterEqualsExpression(GreaterExpression greaterExpression, EqualsExpression equalsExpression) {
        this.greaterExpression = greaterExpression;
        this.equalsExpression = equalsExpression;
    }

    public GreaterEqualsExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【>=】表达式不能为空");
        greaterExpression = new GreaterExpression(ruleExpression.replaceAll(ExpressionEnum.GREATER_EQUALS.getSeparator(), ExpressionEnum.GREATER.getSeparator()));
        equalsExpression = new EqualsExpression(ruleExpression.replaceAll(ExpressionEnum.GREATER_EQUALS.getSeparator(), ExpressionEnum.EQUALS.getSeparator()));
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        return greaterExpression.interpret(stats) || equalsExpression.interpret(stats);
    }
}
