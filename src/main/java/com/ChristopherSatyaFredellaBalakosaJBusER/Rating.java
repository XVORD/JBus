package com.ChristopherSatyaFredellaBalakosaJBusER;
/**
 * Rating Class
 * Represents a rating system where ratings can be added, and the total,
 * count, and average of all ratings can be calculated.
 * @author Christopher Satya
 */
public class Rating
{
    private long count; // The number of ratings
    private long total; // The sum of all rating values

    /**
     * Constructs a new Rating object with initial values.
     * Initializes total and count to zero.
     */
 public Rating(){
     this.total = 0;
     this.count = 0;
 }
    /**
     * Adds a new rating to the total and increments the count.
     * @param rating The rating value to be added.
     */
 public void insert(int rating){
     total += rating;
     count += 1;
 }
    /**
     * Retrieves the total sum of all ratings.
     * @return The total sum of ratings.
     */
 public long getTotal(){
     return total;
 }
    /**
     * Retrieves the count of all ratings.
     * @return The count of ratings.
     */
 public long getCount(){
     return count;
 }
    /**
     * Calculates and returns the average of all ratings.
     * Returns 0 if no ratings have been added.
     * @return The average of all ratings or 0 if no ratings are present.
     */
 public double getAverage(){
     if (count < 1){
         return 0;
     }else{
         return total / count;
    }
 }
    /**
     * Returns a string representation of the Rating object, including the count and total of ratings.
     * @return A string representation of the Rating object.
     */
 public String toString(){
    return "\nCount :" + this.count + "\nTotal :" + this.total;
 }
}
