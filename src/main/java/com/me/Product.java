package com.me;

/**
 * Represents a product on the aisle
 */
public class Product
{
    private String description;
    private double price;
    private String category;

    public Product()
    {

    }

    public Product(String description, double price, String category)
    {
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
