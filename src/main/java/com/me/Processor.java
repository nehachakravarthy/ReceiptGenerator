package com.me;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The processor is responsible for processing product purchases and receipts
 */
public class Processor
{
    private Map<String, TaxCode> taxCodes;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Processor()
    {
        loadTaxCodes();
    }

    /**
     * Load the state specific tax codes
     */
    private void loadTaxCodes()
    {
        String taxCodeLocation = System.getProperty("taxCodes", "./resources/tax-code.properties");

        if (taxCodeLocation != null)
        {
            File taxCodeFile = new File(taxCodeLocation);

            if (taxCodeFile.exists())
            {
                Scanner in = null;
                try
                {
                    in = new Scanner(taxCodeFile);
                    taxCodes = new HashMap<String, TaxCode>();

                    while (in.hasNext())
                    {
                        try
                        {
                            String json = in.nextLine();
                            TaxCode taxCode = OBJECT_MAPPER.readValue(json, TaxCode.class);
                            taxCodes.put(taxCode.getState(), taxCode);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }

                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Generate a receipt from the products
     *
     * @param productQuantity
     * @param location
     * @return
     */
    public Receipt getReceipt(Map<Product, Integer> productQuantity, String location)
    {
        Receipt receipt = new Receipt();
        double subtotal = calculateSubTotal(productQuantity);
        double tax = calculateTax(productQuantity, location);
        double total = calculateTotal(subtotal, tax);
        receipt.setProductQuantity(productQuantity);
        receipt.setTax(tax);
        receipt.setSubtotal(subtotal);
        receipt.setTotal(total);
        return receipt;
    }

    private double calculateSubTotal(Map<Product, Integer> productQuantity)
    {
        double subtotal = 0;
        for (Product product : productQuantity.keySet())
        {
            subtotal += product.getPrice() * productQuantity.get(product);
        }
        return subtotal;
    }

    private double calculateTotal(double subtotal, double tax)
    {
        return subtotal + tax;
    }


    /**
     * Calculate tax from the tax code
     *
     * @param productQuantity
     * @param location
     * @return
     */
    public double calculateTax(Map<Product, Integer> productQuantity, String location)
    {

        double tax = 0;
        TaxCode taxCode = taxCodes.get(location.toUpperCase());
        if (taxCode != null)
        {
            for (Product product : productQuantity.keySet())
            {
                if (!taxCode.getExemptions().contains(product.getCategory()))
                {
                    tax += product.getPrice() * productQuantity.get(product) * (taxCode.getTaxRate() / 100);
                }
            }
        }
        return roundOff(tax);
    }


    private static double roundOff(double tax)
    {
        tax = tax * 10;
        int round = (int) tax;
        tax = (round + ((tax - round) > 0.5 ? 1 : 0.5)) / 10;
        return tax;
    }

    public boolean isValidState(String location)
    {
        return taxCodes.containsKey(location.toUpperCase());
    }
}