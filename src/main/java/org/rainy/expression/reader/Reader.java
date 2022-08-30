package org.rainy.expression.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 获取数据接口
 * @author: zhangyu
 * @date: 2021/9/29 0029 16:10
 */
public interface Reader {

    /**
     * 获取数据
     * @return
     */
    List<Map<String, String>> read();

    /**
     * toMap
     * @param fieldValues
     * @param fieldNames
     * @return
     */
    default Map<String, String> toMap(String[] fieldValues, List<String> fieldNames) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < fieldValues.length; i++) {
            String fieldName = fieldNames.get(i);
            map.put(fieldName, fieldValues[i]);
        }
        return map;
    }

}
