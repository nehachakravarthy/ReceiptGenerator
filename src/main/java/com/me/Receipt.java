package com.me;

import java.util.Map;

/**
 * Represents a Receipt
 */
public class Receipt
{
    private Map<Product, Integer> productQuantity;
    private double subtotal;
    private double total;
    private double tax;

    public Receipt()
    {

    }

    public Receipt(Map<Product, Integer> productQuantity, double subtotal, double total, double tax)
    {
        this.productQuantity = productQuantity;
        this.subtotal = subtotal;
        this.total = total;
        this.tax = tax;
    }

    public double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(double subtotal)
    {
        this.subtotal = subtotal;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public double getTax()
    {
        return tax;
    }

    public void setTax(double tax)
    {
        this.tax = tax;
    }

    public Map<Product, Integer> getProductQuantity()
    {
        return productQuantity;
    }

    public void setProductQuantity(Map<Product, Integer> productQuantity)
    {
        this.productQuantity = productQuantity;
    }

    public String toReceipt()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Item\tPrice\tQuantity\n");

        for (Product product : productQuantity.keySet())
        {
            buffer.append(product.getDescription() + "\t" + product.getPrice() + "\t" + productQuantity.get(product)
                    + "\n");
        }

        buffer.append("Subtotal\t" + getSubtotal() + "\n");
        buffer.append("Tax\t" + getTax() + "\n");
        buffer.append("Total\t" + getTotal());

        return buffer.toString();
    }
}
