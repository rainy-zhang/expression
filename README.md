# expression
使用：

```java
// 可以实现Reader接口自定义数据读取逻辑，可以参考FileReader
List<Map<String, String>> data = new FileReader().read();
for (Map<String, String> datum : data) {
    for (Map.Entry<String, String> entry : datum.entrySet()) {
        // Cacheable用来缓存数据字段与表达式之间的映射关系，可以保存在数据库中
        String ruleExpression = Cacheable.fieldExpressionMap.get(entry.getKey());
        if (ruleExpression == null) {
            continue;
        }
        AggregationExpression aggregationExpression = new AggregationExpression(ruleExpression);
        boolean interpret = aggregationExpression.interpret(datum);
        System.out.println(entry.getKey() + "=" + entry.getValue() + ": " + interpret);
    }
}

```

