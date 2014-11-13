package com.gabilheri.formulacalculator.main.logic;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/13/14.
 */
public class ExpressionElement {

    public enum ExpressionType {
        NUMBER, PARENTHESIS, CONSTANT, OPERATOR, DOT, FUNCTION
    }

    private String content;
    private ExpressionType expressionType;

    public ExpressionElement(String content, ExpressionType expressionType) {
        this.content = content;
        this.expressionType = expressionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }
}
