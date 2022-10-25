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
        fieldExpressionMap.put("workerCode","'' != workerCode AND workerCode.length = 20");
        fieldExpressionMap.put("name", "name != ''");
        fieldExpressionMap.put("age", "age > 0 AND age < 100");
        fieldExpressionMap.put("gender", "gender.length = 1");
        fieldExpressionMap.put("birthDay", "birthDay != ''");
        fieldExpressionMap.put("state", "'' != state AND state IN 1,2");

        fieldNameMap.put("RYRY", Arrays.asList("workerCode", "name", "age", "gender", "birthDay", "state"));
    }

}
