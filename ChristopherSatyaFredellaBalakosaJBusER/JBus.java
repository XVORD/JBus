package ChristopherSatyaFredellaBalakosaJBusER;

public class JBus
{
    public void main(String[] args){
    
    }
        public int getBusId(){
            return 0;
        }
        
        public String getBusName(){
            return "Bus";
        }
        
        public boolean isDiscount(){
            return true ;
        }
        
        public float getDiscountPercentage(int beforeDiscount, int afterDiscount){
            int hasil;
            if (beforeDiscount < afterDiscount){
                return 0.0f;
            }
            else hasil = (beforeDiscount - afterDiscount)/10;
                return hasil;
            
        }
        
        public float getOriginalPrice(int discountedPrice, float discountPercentage){
            float hasil;
            if (discountedPrice < discountPercentage){
                return 0;
            }
            else hasil = (discountedPrice / 1 - discountPercentage);
            return hasil;
        }
        
        public float getDiscountedPrice(int price, float discountPercentage){
            float hasil;
            if (discountPercentage > 100.0f){
                return 0;
            }
            else hasil = price / discountPercentage;
                return hasil;
        }
        
        public float getAdminFeePercentage(){
            return 0.05f;
        }
        
        public int getAdminFee(int price){
            int hasil;
            hasil = price / 20;
            return hasil;
        }
        
        public float getTotalPrice(int price, int numberOfSeat){
            float hasil1;
            float hasil2;
            hasil1 = price * numberOfSeat;
            hasil2 = getAdminFee(price) * 100;
            return hasil1 + hasil2;
        }
    
}
