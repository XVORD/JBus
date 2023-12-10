package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * Invoice Class
 * Represents an invoice for a transaction, containing details about the buyer, renter, timestamp of the transaction, rating, and payment status.
 * @author Christopher Satya
 */
public class Invoice extends Serializable
{
    public Timestamp time;          // Timestamp of the invoice creation
    public int buyerId;             // ID of the buyer
    public int renterId;            // ID of the renter
    public BusRating rating;        // Rating of the bus service
    public PaymentStatus status;    // Status of the payment

    /**
     * Enumeration for representing the rating of a bus service.
     */
    public enum BusRating {
        NONE,       // No rating given
        NEUTRAL,    // Neutral rating
        GOOD,       // Good rating
        BAD         // Bad rating
    }

    /**
     * Enumeration for representing the status of a payment.
     */
    public enum PaymentStatus {
        FAILED,     // Payment failed
        WAITING,    // Payment is pending or waiting
        SUCCESS     // Payment successfully processed
    }

    /**
     * Protected constructor for creating an invoice instance with buyer and renter IDs.
     * Sets default values for time, rating, and status.
     * @param buyerId ID of the buyer
     * @param renterId ID of the renter
     */
    protected Invoice(int buyerId, int renterId){
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = rating.NONE;
        this.status = status.WAITING;
    }
    /**
     * Constructs an Invoice using Account and Renter objects.
     * Initializes the invoice with the current time, buyer and renter IDs, and default values for rating and status.
     * @param buyer The Account object representing the buyer.
     * @param renter The Renter object representing the renter.
     */
    public Invoice(Account buyer, Renter renter){
        super();
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    /**
     * Returns a string representation of the Invoice, including IDs, time, rating, and status.
     * @return A string representation of the Invoice.
     */
    public String toString(){
        return "\nID : " + this.id + "\nTime : " + this.time + "\nBuyer ID : " + this.buyerId + "\nRenter ID : " + this.renterId + "\nRating :" + this.rating + "\nStatus :" + this.status;
    }
}
