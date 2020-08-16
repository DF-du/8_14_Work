package com.example.xiao_mo_day14_01.bean;

public class Beans {
    public Beans() {

    }
    private int id;
    private String name;

    public Beans(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Beans{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
