package com.techelevator.model;

public class Pet {
    private int id;
    private String name;
    private String type;
    private int age;
    boolean isAvailable;
    private String imageLink;

    public Pet(){

    }


    public Pet(int id, String name, String type, int age, boolean isAvalible, String imageLink) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.isAvailable = isAvalible;
        this.imageLink = imageLink;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean avalible) {
        isAvailable = avalible;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
