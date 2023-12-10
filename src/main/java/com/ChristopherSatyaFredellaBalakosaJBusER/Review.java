package com.ChristopherSatyaFredellaBalakosaJBusER;

import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;
/**
 * Review Clas
 * Represents a review with a date and description.
 * This class is used to store and display information about user reviews.
 * @author Christopher Satya
 */
public class Review extends Serializable
{
    public String date;  // The date of the review
    public String desc;  // The descriptive text of the review

    /**
     * Constructs a new Review with the specified date and description.
     * @param date The date when the review was written.
     * @param desc The description or content of the review.
     */
    public Review(String date, String desc){
        super();
        this.date = date;
        this.desc = desc;
    }
    /**
     * The string includes the review's date and description.
     * @return A string representation of the Review object.
     */
    public String toString(){
        return "Date :" + this.date + "\nDesc :" + this.desc;
    }
}
