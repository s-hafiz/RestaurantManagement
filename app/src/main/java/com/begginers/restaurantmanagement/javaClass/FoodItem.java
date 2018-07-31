package com.begginers.restaurantmanagement.javaClass;

/**
 * Created by shafiz on 5/10/2017.
 */

public class FoodItem
{
    private int img_id;
    private String name;
    private String price;

    public FoodItem(int img_id,String name, String price)
    {
        this.setPrice(price);
        this.setName(name);
        this.setImg_id(img_id);
    }

    public int getImg_id()
    {
        return img_id;
    }

    public void setImg_id(int img_id)
    {
        this.img_id = img_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}
