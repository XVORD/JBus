package ChristopherSatyaFredellaBalakosaJBusER;

public class Price
{
 public double price, rebate;
 public int discount;
 
 public Price(double price){
     this.price = price;
     this.discount = 0;
     this.rebate = 0;
 }
 public Price(double price, int discount){
     this.price = price;
     this.discount = discount;
     this.rebate = 0;
 }
 public Price(double price, double discount){
     this.price = price;
     this.rebate = rebate;
     this.discount = 0;
    }
 private double getDiscountedPrice(){
     double hasil;
     if (discount > 100.0){
         hasil = 100.0;
     }else if(discount == 100.0){
         hasil = 0.0;
     }else {
         hasil = ((100-discount) * price)/100;
     }
     return hasil;
 }
 private double getRebatedPrice(){
     double hasil;
     if (rebate > price){
         hasil = 0;
     }else {
         hasil = price - rebate;
     }
     return hasil;
 }
}
