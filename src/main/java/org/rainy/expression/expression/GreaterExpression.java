package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: >表达式解析器
 * @Author: zhangyu
 * @Date: 2021/9/28 0028 11:05
 */
public class GreaterExpression implements Expression {

    private final String key1;
    private final String key2;

    public GreaterExpression(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public GreaterExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【>】表达式不能为空");
        String[] elements = ruleExpression.trim().split("\\s+");
        if (elements.length != 3 || !Objects.equals(elements[1], ExpressionEnum.GREATER.getSeparator())) {
            throw new ExpressionException("【>】表达式格式错误");
        }
        this.key1 = elements[0].trim();
        this.key2 = elements[2].trim();
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        Objects.requireNonNull(stats);
        if (stats.containsKey(this.key1)) {
            return Integer.parseInt(stats.get(this.key1)) > Integer.parseInt(this.key2);
        }
        if (stats.containsKey(this.key2)) {
            return Integer.parseInt(stats.get(this.key2)) > Integer.parseInt(this.key1);
        }
        return false;
    }

}
