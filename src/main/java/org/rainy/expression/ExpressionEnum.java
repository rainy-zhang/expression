package org.rainy.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhangyu
 * @date: 2021/9/28 0028 11:20
 */
public enum ExpressionEnum {

    GREATER(">", "大于"),
    GREATER_EQUALS(">=", "大于等于"),
    LESS("<", "小于"),
    LESS_EQUALS("<=", "小于等于"),
    EQUALS("=", "等于"),
    NOT_EQUALS("!=", "不等于"),
    IN("in", "in"),
    NOT_IN("!in", "not in"),
    AND("and", "与"),
    OR("or", "或"),
    BRACKET_OPEN("(", "左括号"),
    BRACKET_CLOSE(")", "右括号"),
    TRUE("true", "真"),
    FALSE("false", "假"),
    LENGTH(".length", "长度"),
    PATTERN(".pattern", "正则"),
    IF("if", "如果"),
    ;

    private final String separator;
    private final String desc;
    private static List<String> separators = new ArrayList<>();

    ExpressionEnum(String separator, String desc) {
        this.separator = separator;
        this.desc = desc;
    }

    /**
     * 获取所有特殊字符
     * @return [>,<,=,in,!in....]
     */
    public static List<String> allSeparator() {
        if (separators.isEmpty()) {
            separators = Arrays.stream(ExpressionEnum.values()).map(ExpressionEnum::getSeparator).collect(Collectors.toList());
        }
        return separators;
    }

    public String getSeparator() {
        return separator;
    }

    public String getDesc() {
        return desc;
    }
}
