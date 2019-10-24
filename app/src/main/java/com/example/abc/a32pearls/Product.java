package com.example.abc.a32pearls;

/**
 * Created by ABC on 2/21/2018.
 */

public class Product {
    private String id,name;
    private int age;

    public Product(String id,String name,int age){
        this.setId(id);
        this.setName(name);
        this.setAge(age);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
