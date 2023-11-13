package com.ChristopherSatyaFredellaBalakosaJBusER;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
public class Payment extends Invoice {
    private int busId;
    public Timestamp departureDate;
    public List<String> busSeats;
    public Payment(int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeats = new ArrayList<String>();
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyer, renter);
        this.busId = busId;
        this.busSeats = new ArrayList<String>();
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus){
        Schedule schedule = availableSchedule(departureSchedule, seats, bus);
        if(schedule != null){
            schedule.bookSeat(seats);
            return true;
        }
        return false;
    }
    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus){
        for (Schedule schedule : bus.schedules){
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seats)){
                return schedule;
            }
        }
        return null;
    }
    /*public static boolean isAvailable(Timestamp departureSchedule, String seat,  Bus bus) {
        for(Schedule s : bus.schedules){
            if(s.isSeatAvailable(seat) == true){
                return true;
            }
        }
        
        return false;    
    }*/

    public static boolean makeBooking(Timestamp departureSchedule, String seat,  Bus bus) {
        return makeBooking(departureSchedule, List.of(seat), bus);
    }
    public int getBusId() {
        return this.busId;
    }
    public String getDepartureInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "\nDeparture Date: " + dateFormat.format(departureDate.getTime()) + "\nID : " + this.id + "\nBuyer ID : "
                + super.buyerId + "\nRenter ID : " + super.renterId + "\nBus ID : " + this.busId
                + "\nDeparture Date : " + this.departureDate + "\nBus Seat : " + this.busSeats;
    }
    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return timeFormat.format(departureDate.getTime());
    }
}
