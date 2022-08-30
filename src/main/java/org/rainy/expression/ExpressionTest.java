package org.rainy.expression;

import com.rainy.expression.common.Cacheable;
import com.rainy.expression.reader.FileReader;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhangyu
 * @date: 2021/9/28 0028 14:27
 */
public class ExpressionTest {

    public static void main(String[] args) {
        List<Map<String, String>> data = new FileReader().read();
        for (Map<String, String> datum : data) {
            for (Map.Entry<String, String> entry : datum.entrySet()) {
                String ruleExpression = Cacheable.fieldExpressionMap.get(entry.getKey());
                if (ruleExpression == null) {
                    System.out.println("表达式为空：" + entry.getKey());
                    continue;
                }
                AggregationExpression aggregationExpression = new AggregationExpression(ruleExpression);
                boolean interpret = aggregationExpression.interpret(datum);
                System.out.println(entry.getKey() + "=" + entry.getValue() + ": " + interpret);
            }
        }
    }

}
