package com.ChristopherSatyaFredellaBalakosaJBusER;

public class Price
{
 public double price, rebate;
 
 public Price(double price){
     this.price = price;
     this.rebate = 0;
 }
 public Price(double price, double discount){
    this.price = price;
    this.rebate = rebate;
 }
 public String toString(){
    return "\nPrice :" + this.price + "\nRebate :" + this.rebate;
 }
}
