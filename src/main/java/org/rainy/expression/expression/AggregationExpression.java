package org.rainy.expression.expression;

import java.util.Map;

/**
 * @description:
 * @author: zhangyu
 * @date: 2021/9/28 0028 14:49
 */
public class AggregationExpression {

    private final Expression expression;

    public AggregationExpression(String ruleExpression) {
        expression = new BracketExpression(ruleExpression);
    }

    public boolean interpret(Map<String, String> stats) {
        return expression.interpret(stats);
    }

}
