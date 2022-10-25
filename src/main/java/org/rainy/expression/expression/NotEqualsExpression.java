package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @description: !=表达式解析器
 * @author: zhangyu
 * @date: 2021/9/29 0029 14:33
 */
public class NotEqualsExpression implements Expression {

    private final String key1;
    private final String key2;

    public NotEqualsExpression(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public NotEqualsExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【!=】表达式不能为空");
        String[] elements = ruleExpression.trim().split("\\s+");
        if (elements.length != 3 || !Objects.equals(elements[1], ExpressionEnum.NOT_EQUALS.getSeparator())) {
            throw new ExpressionException("【!=】表达式字符串格式错误");
        }
        this.key1 = elements[0];
        this.key2 = toEmpty(elements[2]);
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        Objects.requireNonNull(stats);
        if (stats.containsKey(this.key1)) {
            return !Objects.equals(stats.get(this.key1), this.key2);
        }
        if (stats.containsKey(this.key2)) {
            return !Objects.equals(stats.get(this.key2), this.key1);
        }
        return false;
    }

}
