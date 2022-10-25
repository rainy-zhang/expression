package org.rainy.expression.expression;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: 解释器接口
 * @Author: zhangyu
 * @Date: 2021/9/28 0028 11:02
 */
public interface Expression {

    /**
     * 解析逻辑
     * @param stats key -> Object, value -> fieldName
     * @return
     */
    boolean interpret(Map<String, String> stats);

    default String toEmpty(String str) {
        Objects.requireNonNull(str);
        return str.trim().replaceAll("null", "").replaceAll("'", "");
    }

}
