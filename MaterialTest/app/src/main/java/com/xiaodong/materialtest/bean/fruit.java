package com.xiaodong.materialtest.bean;

/**
 * Created by yxd on 2017/2/17.
 */
public class Fruit {
    int id;
    String name;

    public Fruit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
