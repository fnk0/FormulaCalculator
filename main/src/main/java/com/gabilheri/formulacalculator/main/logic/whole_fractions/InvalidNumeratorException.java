package com.gabilheri.formulacalculator.main.logic.whole_fractions;

/**
 * Created by dawkins on 7/16/14.
 */
public class InvalidNumeratorException extends RuntimeException {

    public InvalidNumeratorException() { super(); }
    public InvalidNumeratorException(String message) { super(message); }
    public InvalidNumeratorException(String message, Throwable cause) { super(message, cause); }
    public InvalidNumeratorException(Throwable cause) { super(cause); }

}
