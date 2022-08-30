package org.rainy.expression;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description: and表达式解析器
 * @author: zhangyu
 * @date: 2021/9/28 0028 11:08
 */
public class AndExpression implements Expression {

    private List<Expression> expressions = new LinkedList<>();

    public AndExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public AndExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【and】表达式不能为空");
        String[] ruleExpressions = ruleExpression.trim().split(ExpressionEnum.AND.getSeparator());
        for (String subExpression: ruleExpressions) {
            if (subExpression.isEmpty()) {
                continue;
            }
            expressions.add(ExpressionFactory.getExpression(subExpression.trim()));
        }
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        if (expressions.isEmpty()) {
            return true;
        }
        for (Expression expression : expressions) {
            if (!expression.interpret(stats)) {
                return false;
            }
        }
        return true;
    }

}
