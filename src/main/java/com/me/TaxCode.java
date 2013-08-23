package com.me;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the tax code for a state
 */
public class TaxCode
{
    private String state;
    private double taxRate;
    private Set<String> exemptions;

    public TaxCode()
    {

    }

    public TaxCode(String state)
    {
        this.state = state;
        taxRate = 1d;
        exemptions = new HashSet<String>();
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public double getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(double taxRate)
    {
        this.taxRate = taxRate;
    }

    public Set<String> getExemptions()
    {
        return exemptions;
    }

    public void setExemptions(Set<String> exemptions)
    {
        this.exemptions = exemptions;
    }
}
