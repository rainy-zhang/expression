# expression
之前做过一个数据校验服务，入参是一个文本数据，需要筛选出不符合规则的数据，格式：field1;field2;field3;....  
最开始的思路是将每种类型的文件映射为java对象，然后通过hibernate-validate注解对对象属性做一系列的约束，最后校验这些对象是否满足条件约束。
其中比较头疼的一点是，同一行数据的多个字段是有关联性的，比如field1=1时field2可以为空，field1=2时field2不可以为空，虽然可以通过自定义注解，但是实现起来十分麻烦。
并且由于需要把大量的文本转换为java对象，内存占用率很高且效率十分低下。
最痛苦的是数据规则可能会发生变化，每次都需要修改代码，并重新打包部署上线，非常麻烦。

后来在学习**解释器模式**时发现可以自定义表达式来解决上述问题。  
根据规则预先定义好相关的表达式，然后动态配置表达式与数据字段之间的关联关系，校验时获取字段对应的表达式校验数据是否合法。  
使用：
```java
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

```

- 简单表达式（单个字段校验）：  
    - 格式：filed1 > ? and field2 < ?  
    - 例如：
        - (校验name字段不等于zhangsan并且age大于18): name != 'zhangsan' and age > 18
        - (校验name字段等于wangwu或者name字段等于lisi): name = 'wangwu' or name = 'lisi'



- 数据关联性表达式（多个字段校验）：  
    - 格式：if[field1 = ?]{field2 = ?}  
    - 例如：
        - (state=1时，校验code字段不能为空): if[state = 1]{ code != '' }
        - (state=1并且name不等于空时，校验value字段不能为空): if[state = 1 and name != '']{value != ''}  


- 内置函数：目前只写了`length`和`pattern`这两个函数：length用来校验数据长度，pattern通过正则校验数据
    - 格式：field.函数名 = ?  
    - 例如：
        - (校验name字段长度大于5): name.length > 5
        - (校验code字段满足正则): code.pattern = \wd+


