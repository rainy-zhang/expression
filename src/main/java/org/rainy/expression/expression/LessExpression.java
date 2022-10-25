package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: <表达式解析器
 * @Author: zhangyu
 * @Date: 2021/9/28 0028 11:07
 */
public class LessExpression implements Expression {

    private final String key1;
    private final String key2;

    public LessExpression(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public LessExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【<】表达式不能为空");
        String[] elements = ruleExpression.trim().split("\\s+");
        if (elements.length != 3 || !Objects.equals(elements[1], ExpressionEnum.LESS.getSeparator())) {
            throw new ExpressionException("【<】表达式格式错误");
        }
        key1 = elements[0];
        key2 = elements[2];
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        Objects.requireNonNull(stats);
        if (stats.containsKey(key1)) {
            return Integer.parseInt(stats.get(key1)) < Integer.parseInt(key2);
        }
        if (stats.containsKey(key2)) {
            return Integer.parseInt(stats.get(key2)) < Integer.parseInt(key1);
        }
        return false;
    }
}
