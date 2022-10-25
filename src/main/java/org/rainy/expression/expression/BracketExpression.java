package org.rainy.expression.expression;

import java.util.*;

/**
 * @description: ()表达式解析器
 * @author: zhangyu
 * @date: 2021/9/28 0028 14:54
 */
public class BracketExpression implements Expression {

    private final List<Expression> expressions = new LinkedList<>();
    /**
     * 用来保存表达式中间的连接符
     */
    private final Map<String, Object> connectorMap = new HashMap<>();
    private final String open = ExpressionEnum.BRACKET_OPEN.getSeparator();
    private final String close = ExpressionEnum.BRACKET_CLOSE.getSeparator();

    public BracketExpression(String ruleExpression) {
        Objects.requireNonNull(ruleExpression, "()表达式不能为空");
        handleExpression(ruleExpression);
    }

    private void handleExpression(String ruleExpression) {
        Stack<String> expressionStack = new Stack<>();
        String[] elements = ruleExpression.split("\\s+");
        // 处理括号中的表达式
        for (String element : elements) {
            if (element.contains(open)) {
                expressionStack.push(element.substring(0, 1));
                // 括号与其它字符之间可能没有空格，所以需要额外处理一下
                if (!Objects.equals(open, element)) {
                    expressionStack.push(element.substring(1));
                }
            } else if (element.contains(close)) {
                if (!Objects.equals(close, element)) {
                    expressionStack.push(element.substring(0, element.length() - 1));
                }
                Expression expression = analysis(expressionStack);
                expressions.add(expression);
            } else {
                expressionStack.push(element);
            }
        }

        // 处理不包含括号的表达式
        StringBuilder expressionBuilder = new StringBuilder();
        for (String element : expressionStack) {
            // 集合不为空，说明表达式中包含括号，需要把连接符单独保存下来
            if (ExpressionEnum.OR.getSeparator().equals(element) || ExpressionEnum.AND.getSeparator().equals(element) && expressions.size() > 0) {
                connectorMap.put(element, new Object());
            } else {
                expressionBuilder.append(element).append(" ");
            }
        }
        expressions.add(new OrExpression(expressionBuilder.toString().trim()));
    }

    private Expression analysis(Stack<String> expressionStack) {
        // 栈是先进后出，取出来的表达式和原式是相反的: age == 10 -> 10 == age, 所以需要再出入一次栈 10 == age -> age == 10
        // 入栈：age == 10 -> 10 == age
        Stack<String> reverseStack = new Stack<>();
        while (!expressionStack.isEmpty()) {
            String e = expressionStack.pop();
            if (open.equals(e)) {
                break;
            }
            reverseStack.push(e);
        }
        // 出栈：10 == age -> age == 10
        StringBuilder expressionBuilder = new StringBuilder();
        while (!reverseStack.isEmpty()) {
            String e = reverseStack.pop();
            expressionBuilder.append(e).append(" ");
        }
        return new OrExpression(expressionBuilder.toString().trim());
    }

    @Override
    public boolean interpret(Map<String, String> stats) {
        List<Boolean> results = new ArrayList<>();
        for (Expression expression : expressions) {
            results.add(expression.interpret(stats));
        }

        // or 的优先级比 and 更高
        if (connectorMap.containsKey(ExpressionEnum.OR.getSeparator())) {
            return results.stream().anyMatch(e -> e);
        } else if (connectorMap.containsKey(ExpressionEnum.AND.getSeparator())) {
            return results.stream().allMatch(e -> e);
        } else {
            return results.size() > 0 ? results.get(0) : false;
        }
    }

}
