package org.rainy.expression;

import java.util.Objects;

/**
 * @description:
 * @author: zhangyu
 * @date: 2021/9/28 0028 14:40
 */
public class ExpressionFactory {

    public static Expression getExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "表达式不能为空");
        String[] elements = ruleExpression.trim().split("\\s+");
        if (ruleExpression.toLowerCase().startsWith(ExpressionEnum.IF.getSeparator())) {
            return new IfExpression(ruleExpression);
        } else if (ruleExpression.toLowerCase().equals(ExpressionEnum.TRUE.getSeparator())) {
            return new TrueExpression();
        } else if (ruleExpression.toLowerCase().equals(ExpressionEnum.FALSE.getSeparator())) {
            return new FalseExpression();
        } else if (elements[0].split("\\.")[0].toLowerCase().equals(ExpressionEnum.LENGTH.getSeparator())) {
            return new LengthExpression(ruleExpression);
        } else if (elements[0].split("\\.")[0].toLowerCase().equals(ExpressionEnum.PATTERN.getSeparator())) {
            return new PatternExpression(ruleExpression);
        } else if (elements[1].equals(ExpressionEnum.GREATER_EQUALS.getSeparator())) {
            return new GreaterEqualsExpression(ruleExpression);
        } else if (elements[1].equals(ExpressionEnum.GREATER.getSeparator())) {
            return new GreaterExpression(ruleExpression);
        } else if (elements[1].equals(ExpressionEnum.LESS_EQUALS.getSeparator())) {
            return new LessEqualsExpression(ruleExpression);
        } else if (elements[1].equals(ExpressionEnum.LESS.getSeparator())) {
            return new LessExpression(ruleExpression);
        } else if (elements[1].equalsIgnoreCase(ExpressionEnum.EQUALS.getSeparator())) {
            return new EqualsExpression(ruleExpression);
        } else if (elements[1].equalsIgnoreCase(ExpressionEnum.NOT_EQUALS.getSeparator())) {
            return new NotEqualsExpression(ruleExpression);
        } else if (elements[1].equalsIgnoreCase(ExpressionEnum.IN.getSeparator())) {
            return new InExpression(ruleExpression);
        } else if (elements[1].equalsIgnoreCase(ExpressionEnum.NOT_IN.getSeparator())) {
            return new NotInExpression(ruleExpression);
        } else {
            throw new ExpressionException("表达式无法解析：" + ruleExpression);
        }
    }

}
