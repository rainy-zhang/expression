package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: 等于表达式解析器
 * @Author: zhangyu
 * @Date: 2021/9/28 0028 11:08
 */
public class EqualsExpression implements Expression {

    private final String key1;
    private final String key2;

    public EqualsExpression(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public EqualsExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【等于】表达式不能为空");
        String[] elements = ruleExpression.trim().split("\\s+");
        // elements[0] = 'key', elements[1] = '==', elements[2] = 'ruleValue'
        if (elements.length != 3 || !Objects.equals(elements[1], ExpressionEnum.EQUALS.getSeparator())) {
            throw new ExpressionException("【等于】表达式解析错误");
        }
        this.key1 = toEmpty(elements[0]);
        this.key2 = toEmpty(elements[2]);
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        Objects.requireNonNull(stats);
        if (stats.containsKey(this.key1)) {
            return Objects.equals(stats.get(this.key1), this.key2);
        }
        if (stats.containsKey(this.key2)) {
            return Objects.equals(stats.get(this.key2), this.key1);
        }
        return false;
    }
}
