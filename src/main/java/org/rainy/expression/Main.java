package org.rainy.expression;

import org.rainy.expression.common.Cacheable;
import org.rainy.expression.expression.AggregationExpression;
import org.rainy.expression.reader.FileReader;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangyu
 */
public class Main {

    public static void main(String[] args) {
        // 可以实现Reader接口自定义数据读取逻辑，可以参考FileReader
        List<Map<String, String>> data = new FileReader().read();
        for (Map<String, String> datum : data) {
            for (Map.Entry<String, String> entry : datum.entrySet()) {
                // 根据字段名称获取对应的表达式。（可以在数据库中建立映射关系）
                String ruleExpression = Cacheable.fieldExpressionMap.get(entry.getKey());
                if (ruleExpression == null) {
                    continue;
                }
                AggregationExpression aggregationExpression = new AggregationExpression(ruleExpression);
                // interpret方法参数格式：key表示字段名，value表示字段值
                boolean interpret = aggregationExpression.interpret(datum);
                System.out.println(entry.getKey() + "=" + entry.getValue() + ": " + interpret);
            }
        }

    }
    
}
