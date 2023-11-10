package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

import java.sql.Timestamp;

public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    public enum BusRating{
        NONE,NEUTRAL, GOOD, BAD
    }
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS
    }
    protected Invoice(int buyerId, int renterId){
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = rating.NONE;
        this.status = status.WAITING;
    }
    public Invoice(Account buyer, Renter renter){
        super();
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    public String toString(){
        return "\nID : " + this.id + "\nTime : " + this.time + "\nBuyer ID : " + this.buyerId + "\nRenter ID : " + this.renterId + "\nRating :" + this.rating + "\nStatus :" + this.status;
    }
}
