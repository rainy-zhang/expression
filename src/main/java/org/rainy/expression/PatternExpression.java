package org.rainy.expression;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @description: pattern表达式解析器
 * @author: zhangyu
 * @date: 2021/9/30 0030 11:55
 */
public class PatternExpression implements Expression {

    private final String realKey;
    private final String regex;
    private final Pattern pattern;

    public PatternExpression(String realKey, String regex, Pattern pattern) {
        this.realKey = realKey;
        this.regex = regex;
        this.pattern = pattern;
    }

    // name.pattern = \wd+
    // \wd+ = name.pattern
    public PatternExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "【pattern】表达式不能为空");
        String[] elements = ruleExpression.split("\\s+");
        if (elements.length != 3
                || !Objects.equals(elements[1], ExpressionEnum.EQUALS.getSeparator())) {
            throw new ExpressionException("【pattern】表达式格式错误");
        }
        if (elements[0].contains(ExpressionEnum.PATTERN.getSeparator())) {
            this.realKey = elements[0].split("\\.")[0];
            this.regex = elements[2];
        }else {
            this.realKey = elements[2].split("\\.")[0];
            this.regex = elements[0];
        }
        this.pattern = Pattern.compile(this.regex);
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        if (!stats.containsKey(this.realKey)) {
            return false;
        }
        return pattern.matcher(stats.get(this.realKey)).matches();
    }
}
