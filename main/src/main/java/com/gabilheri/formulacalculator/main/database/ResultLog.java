package com.gabilheri.formulacalculator.main.database;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class ResultLog {

    private long id;
    private String input;
    private String result;
    private String createdAt;

    public ResultLog() {}

    public ResultLog(String input, String result) {
        this.input = input;
        this.result = result;
    }

    public ResultLog(long id, String input, String result) {
        this.id = id;
        this.input = input;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public ResultLog setId(int id) {
        this.id = id;
        return this;
    }

    public String getInput() {
        return input;
    }

    public ResultLog setInput(String input) {
        this.input = input;
        return this;
    }

    public String getResult() {
        return result;
    }

    public ResultLog setResult(String result) {
        this.result = result;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ResultLog setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
