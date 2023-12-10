package com.ChristopherSatyaFredellaBalakosaJBusER;
import java.util.ArrayList;
/**
 * Validate Class
 * The Validate class provides static methods for filtering and validating
 * price-related data.
 * @author Christopher Satya
 */
public class Validate
{
    /**
     * Filters an array of Price objects based on a specified value.
     * It returns an ArrayList containing the prices from the array that either
     * are less than or equal to, or greater than the specified value,
     * based on the less parameter.
     *
     * @param list  An array of Price objects to be filtered.
     * @param value The value to compare each Price object's price against.
     * @param less  A boolean that determines the type of comparison:
     *              If true, the method includes prices less than or equal to value.
     *              If false, it includes prices greater than value.
     * @return      An ArrayList of Double containing the filtered prices.
     */
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
