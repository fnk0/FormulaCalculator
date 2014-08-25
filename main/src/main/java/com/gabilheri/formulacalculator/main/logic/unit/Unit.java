package com.gabilheri.formulacalculator.main.logic.unit;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/24/14.
 */
public class Unit {

    private String name;
    private int type, group;

    public Unit() {
    }

    public Unit(String name, int type, int group) {
        this.name = name;
        this.type = type;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public Unit setName(String name) {
        this.name = name;
        return this;
    }

    public int getType() {
        return type;
    }

    public Unit setType(int type) {
        this.type = type;
        return this;
    }

    public int getGroup() {
        return group;
    }

    public Unit setGroup(int group) {
        this.group = group;
        return this;
    }
}
