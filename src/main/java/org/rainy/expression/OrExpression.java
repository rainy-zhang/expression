package org.rainy.expression;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: or表达式解析器
 * @Author: zhangyu
 * @Date: 2021/9/28 0028 11:09
 */
public class OrExpression implements Expression {

    private List<Expression> expressions = new LinkedList<>();

    public OrExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public OrExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【or】表达式不能为空");
        String[] strExpressions = ruleExpression.split(ExpressionEnum.OR.getSeparator());
        for (String subExpression : strExpressions) {
            expressions.add(new AndExpression(subExpression.trim()));
        }
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        if (expressions.isEmpty()) {
            return true;
        }
        for (Expression expression : expressions) {
            if (expression.interpret(stats)) {
                return true;
            }
        }
        return false;
    }

}
