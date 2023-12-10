package com.ChristopherSatyaFredellaBalakosaJBusER;
/**
 * Price Class
 * Represents a price object that includes the base price and a potential rebate amount.
 * @author Christopher Satya
 */
public class Price
{
    public double price;  // The base price
    public double rebate; // The rebate amount on the price

    /**
     * Constructs a new Price object with the specified base price.
     * Initializes the rebate amount to zero.
     * @param price The base price.
     */
 public Price(double price){
     this.price = price;
     this.rebate = 0;
 }
    /**
     * Constructs a new Price object with the specified base price and rebate amount.
     * @param price   The base price.
     * @param rebate  The rebate amount on the price.
     */
 public Price(double price, double rebate){
    this.price = price;
    this.rebate = rebate;
 }
    /**
     * Returns a string representation of the Price object, including the base price and rebate amount.
     * @return A string representation of the Price object.
     */
 public String toString(){
    return "\nPrice :" + this.price + "\nRebate :" + this.rebate;
 }
}
