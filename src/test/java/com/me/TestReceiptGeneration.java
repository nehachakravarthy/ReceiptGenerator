package com.me;

import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test the generation of receipts
 */
public class TestReceiptGeneration
{
    private static final Processor PROCESSOR = new Processor();
    private static Logger logger = Logger.getLogger(TestReceiptGeneration.class);

    @Test
    public void useCase1()
    {
        Map<Product, Integer> products = new HashMap<Product, Integer>();

        products.put(new Product("book",12.99,"books"), 1);
        products.put(new Product("chips",3.99,"food"), 1);

        Receipt receipt = PROCESSOR.getReceipt(products, "CA");

        logger.info(receipt.toReceipt());

        Assert.assertTrue(receipt.getTotal() == 18.28);
        Assert.assertTrue(receipt.getTax() == 1.3);
    }

    @Test
    public void useCase2()
    {
        Map<Product, Integer> products = new HashMap<Product, Integer>();

        products.put(new Product("book",12.99,"books"), 1);
        products.put(new Product("music cd",9.99,"music"), 3);

        Receipt receipt = PROCESSOR.getReceipt(products, "NJ");

        logger.info(receipt.toReceipt());

        Assert.assertTrue(receipt.getTotal() == 46.01);
        Assert.assertTrue(receipt.getTax() == 3.05);
    }

    @Test
    public void useCase3()
    {
        Map<Product, Integer> products = new HashMap<Product, Integer>();

        products.put(new Product("music cd",9.99,"music"), 2);
        products.put(new Product("sweater",29.99,"clothing"), 1);

        Receipt receipt = PROCESSOR.getReceipt(products, "NJ");

        logger.info(receipt.toReceipt());

        Assert.assertTrue(receipt.getTotal() == 51.37);
        Assert.assertTrue(receipt.getTax() == 1.4);
    }
}
