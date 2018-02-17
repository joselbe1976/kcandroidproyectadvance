package com.joselbe.domain.model;


public class ShopJava {
    private int id;
    private String name;
    private String address;

    public ShopJava(String name){
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    //constructor anónimo
    {
        ShopJava shop1 = new ShopJava("Tienda 1");
        shop1.setAddress("Calle valmores");

    }
}

