package com.example.myapplication;

public class Place {
    private int id;
    private String name;

    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Place(String name) {
        this.name = name;
    }

    public Place() {
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
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
