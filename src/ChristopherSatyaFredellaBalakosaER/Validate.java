package ChristopherSatyaFredellaBalakosaJBusER;
import java.util.ArrayList;

public class Validate
{
    public static ArrayList filter(Price[] list, int value, boolean less){
        
        ArrayList<Double> array = new ArrayList<>();
        
        if(less == true){
            
            for(int i = 0; i < list.length; i++){
                
                if(list[i].price <= value){
                    
                    array.add(list[i].price);
                }
            }
        }
        
        else{
            
            for(int i = 0; i < list.length; i++){
                
                if(list[i].price > value){
                    
                    array.add(list[i].price);
                }
            }
        }
        
        return array;
    }
}
