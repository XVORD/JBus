package ChristopherSatyaFredellaBalakosaJBusER;

public class Rating
{
    private long count,total;
 
 public Rating(){
     this.total = 0;
     this.count = 0;
 }
 public void insert(int rating){
     total += rating;
     count += 1;
 }
 public long getTotal(){
     return total;
 }
 public long getCount(){
     return count;
 }
 public double getAverage(){
     if (count < 1){
         return 0;
     }else{
         return total / count;
    }
 }
 public String toString(){
    return "\nCount :" + this.count + "\nTotal :" + this.total;
 }
}
