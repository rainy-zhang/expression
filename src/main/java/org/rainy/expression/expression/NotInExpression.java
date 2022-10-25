package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: zhangyu
 * @date: 2021/9/30 0030 15:58
 */
public class NotInExpression implements Expression {

    private final InExpression inExpression;

    public NotInExpression(InExpression inExpression) {
        this.inExpression = inExpression;
    }

    public NotInExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【not in】表达式不能为空");
        this.inExpression = new InExpression(ruleExpression.replace("!", ""));
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        return !inExpression.interpret(stats);
    }

}
