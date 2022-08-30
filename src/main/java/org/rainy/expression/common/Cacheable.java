package org.rainy.expression.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhangyu
 * @date: 2021/9/29 0029 16:24
 */
public class Cacheable {

    public static Map<String, String> fieldExpressionMap = new HashMap<>();
    public static Map<String, List<String>> fieldNameMap = new HashMap<>();
    static {
        fieldExpressionMap.put("state", "'' != state");
        fieldExpressionMap.put("time", "if[state != 2]{ time != ''}");

        fieldNameMap.put("RYRY", Arrays.asList("state", "time"));//, "age", "job", "department"));
    }

}
