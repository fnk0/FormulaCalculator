package com.gabilheri.formulacalculator.main.logic;

import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.Stack;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/13/14.
 */
public class Expression extends Stack<ExpressionElement> {

    private Stack tempStack;

    public Expression() {
        tempStack = new Stack();
    }

    @Override
    public synchronized String toString() {

        StringBuilder builder = new StringBuilder();

        while(this.size() > 0) {
            builder.append(this.pop().getContent());
        }

        StringBuilder b = new StringBuilder(Utils.stripHTML(builder.toString()));
        b.reverse();
        return b.toString();
    }
}
