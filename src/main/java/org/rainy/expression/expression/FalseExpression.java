package org.rainy.expression.expression;

import java.util.Map;

/**
 * @description: false表达式解析器
 * @author: zhangyu
 * @date: 2021/9/29 0029 17:56
 */
public class FalseExpression implements Expression {

    @Override
    public boolean interpret(Map<String, String> stats) {
        return false;
    }

}
