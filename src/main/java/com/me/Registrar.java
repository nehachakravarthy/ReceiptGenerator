package com.me;


import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Registrar accepts products and generates receipts
 */
public class Registrar
{
    private Scanner in;
    private Object productInfo;
    private int quantity;
    private Processor processor;
    private Map<Product, Integer> productQuantity;
    private String location = null;

    public Registrar()
    {
        in = new Scanner(System.in);
        processor = new Processor();
        productQuantity = new HashMap<Product, Integer>();
    }

    public void readInput()
    {

    }


    public String readString()
    {
        String value = null;

        while ((value = in.nextLine()).length() == 0)
        {
            print("Cannot be an empty value");
        }

        return value;
    }

    public Double readNumber()
    {
        Double value = null;

        while ((value = toDouble(in.nextLine())) <= 0)
        {
            print("Cannot be 0 or negative");
        }

        return value;
    }

    private Double toDouble(String value)
    {
        double number = 0;

        try
        {
            number = Double.parseDouble(value);
        } catch (Exception e)
        {
            number = 0;
        }

        return number;
    }

    public void run()
    {
        while (true)
        {
            if(location == null)
            {
                print("Enter Location");
                location = readLocation();

            }

            print("Enter 1 to Add Product to cart");
            print("Enter 2 to Print Receipt");
            print("Enter 3 for New Transaction");
            print("Enter 4 to Exit");
            print("Enter Selected Option");

            int option = readNumber().intValue();

            switch (option)
            {
                case 1:
                    Product product = getProductInfo();

                    int quantity = getQuantity();

                    if (product != null)
                    {
                        productQuantity.put(product, quantity);
                    }

                    print("Added product : " + product.toString());
                    
                    break;

                case 2:
                    Receipt receipt = processor.getReceipt(productQuantity, location);
                    printReceipt(receipt);
                    break;
                case 3:
                    productQuantity = new HashMap<Product, Integer>();
                    location = null;
                    break;
                case 4:
                    System.exit(0);
                default:
                    print("Try Again !");
                    break;

            }
        }
    }

    private String readLocation()
    {
        String value = null;

        while (!processor.isValidState((value = readString())))
        {
            print("Enter a valid state");
        }

        return value;
    }


    private void print(String msg)
    {
        System.out.println(msg);
    }

    private void printReceipt(Receipt receipt)
    {
        print(receipt.toReceipt());
    }

    public Product getProductInfo()
    {
        Product productInfo = new Product();
        print("Enter product name");
        productInfo.setDescription(readString());
        print("Enter Product category");
        productInfo.setCategory(readString());
        print("Enter Product price");
        productInfo.setPrice(readNumber());


        return productInfo;
    }

    public int getQuantity()
    {
        print("Enter product quantity");
        quantity = readNumber().intValue();
        return quantity;
    }

    public static void main(String args[])
    {
        new Registrar().run();
    }
}
