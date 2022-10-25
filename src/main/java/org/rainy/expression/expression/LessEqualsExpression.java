package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @description: <=表达式解析器
 * @author: zhangyu
 * @date: 2021/9/29 0029 15:49
 */
public class LessEqualsExpression implements Expression {

    private final LessExpression lessExpression;
    private final EqualsExpression equalsExpression;

    public LessEqualsExpression(LessExpression lessExpression, EqualsExpression equalsExpression) {
        this.lessExpression = lessExpression;
        this.equalsExpression = equalsExpression;
    }

    public LessEqualsExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【<=】表达式不能为空");
        lessExpression = new LessExpression(ruleExpression.replaceAll(ExpressionEnum.LESS_EQUALS.getSeparator(), ExpressionEnum.LESS.getSeparator()));
        equalsExpression = new EqualsExpression(ruleExpression.replaceAll(ExpressionEnum.LESS_EQUALS.getSeparator(), ExpressionEnum.EQUALS.getSeparator()));
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        return lessExpression.interpret(stats) || equalsExpression.interpret(stats);
    }
}
