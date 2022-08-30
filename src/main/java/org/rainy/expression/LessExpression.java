package org.rainy.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: <表达式解析器
 * @Author: zhangyu
 * @Date: 2021/9/28 0028 11:07
 */
public class LessExpression implements Expression {

    private final EqualsExpression equalsExpression;

    public LessExpression(EqualsExpression equalsExpression) {
        this.equalsExpression = equalsExpression;
    }

    public LessExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【<】表达式不能为空");
        String[] elements = ruleExpression.trim().split("\\s+");
        if (elements.length != 3 || !Objects.equals(elements[1], ExpressionEnum.LESS.getSeparator())) {
            throw new ExpressionException("【<】表达式格式错误");
        }
        equalsExpression = new EqualsExpression(ruleExpression.replaceAll("!", ""));
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        Objects.requireNonNull(stats);
        return !equalsExpression.interpret(stats);
    }
}
