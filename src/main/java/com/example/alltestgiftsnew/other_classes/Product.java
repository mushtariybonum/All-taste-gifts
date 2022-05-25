package com.example.alltestgiftsnew.other_classes;

public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private String data;
    private String img_src;

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public Product(){}

    public Product(String id, String name, String description, double price, String data, String img_src) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.data = data;
        this.img_src = img_src;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setImgSrc(String img_src) {
        this.img_src = img_src;
    }
}
