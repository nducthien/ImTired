package com.example.asd.imtired.Model;

public class Categori {

    private String Name;
    private String Image;

    public Categori() {
    }

    public Categori(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
