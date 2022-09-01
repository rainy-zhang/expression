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
        // interpret方法参数格式：key表示字段名，value表示字段值
        boolean interpret = aggregationExpression.interpret(datum);
        System.out.println(entry.getKey() + "=" + entry.getValue() + ": " + interpret);
    }
}

```

- 简单表达式（单个字段校验）：  
    - 格式：filed1 > ? and field2 < ?  
    - 例如：
        - name != 'zhangsan' and age > 18
        - name = 'wangwu' or name = 'lisi'



- 数据关联性表达式（多个字段校验）：  
    - 格式：if[field1 = ?]{field2 = ?}  
    - 例如：
        - if[state = 1]{ code != '' }
        - if[state = 1 and name != '']{value != ''}  


- 内置函数：目前只写了`length`和`pattern`这两个函数：length用来校验数据长度，pattern通过正则校验数据
    - 格式：field.函数名 = ?  
    - 例如：
        - name.length > 5
        - code.pattern = \wd+


