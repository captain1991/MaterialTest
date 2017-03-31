package com.xiaodong.materialtest.bean;

import android.util.Log;

import java.util.Random;

/**
 * Created by yxd on 2017/2/17.
 */
public class Fruit {
    int id;
    String name;
    String comName;

    public Fruit(int id, String name) {
        this.id = id;
//        int count;
//        Random random = new Random();
//        count = random.nextInt(50);
//        StringBuilder stringBuffer= new StringBuilder();
//        for(int i=0;i<count;i++){
//            stringBuffer.append(name);
//            Log.e("count",""+i);
//        }
        this.comName = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComName() {
        return comName;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        int count;
        Random random = new Random();
        count = random.nextInt(50);
        StringBuilder stringBuffer= new StringBuilder();
        for(int i=0;i<count;i++){
            stringBuffer.append(name);
            Log.e("count",""+i);
        }
        this.name = stringBuffer.toString();
    }
}
