package com.ChristopherSatyaFredellaBalakosaJBusER;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Payment Class
 * Represents a payment transaction for bus travel, extending the Invoice class with
 * specific attributes related to bus travel such as bus ID, seats, and departure date.
 * @author Christopher Satya
 */
public class Payment extends Invoice {
    private int busId;                       // ID of the bus
    public Timestamp departureDate;          // Date of departure
    public List<String> busSeats;            // List of booked seats

    /**
     * Constructs a new Payment instance using buyer and renter IDs, bus ID, seat information, and departure date.
     * @param buyerId ID of the buyer
     * @param renterId ID of the renter
     * @param busId ID of the bus
     * @param busSeats List of seats being booked
     * @param departureDate Date of departure
     */
    public Payment(int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeats = new ArrayList<String>();
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Constructs a new Payment instance using buyer and renter objects, bus ID, seat information, and departure date.
     * @param buyer Buyer account object
     * @param renter Renter object
     * @param busId ID of the bus
     * @param busSeats List of seats being booked
     * @param departureDate Date of departure
     */
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyer, renter);
        this.busId = busId;
        this.busSeats = new ArrayList<String>();
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Attempts to make a booking for specified seats on a bus at a given departure schedule.
     * @param departureSchedule The intended departure schedule
     * @param seats The list of seats to book
     * @param bus The bus on which booking is to be made
     * @return true if the booking is successful, false otherwise
     */
    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus){
        Schedule schedule = availableSchedule(departureSchedule, seats, bus);
        if(schedule != null){
            schedule.bookSeat(seats);
            return true;
        }
        return false;
    }
    /**
     * Finds an available schedule on a bus that matches the departure schedule and seat availability.
     * @param departureSchedule The intended departure schedule
     * @param seats The list of seats being inquired about
     * @param bus The bus to check for an available schedule
     * @return Schedule if available, null otherwise
     */
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
    /**
     * Convenience method to make a booking for a single seat.
     * @param departureSchedule  The intended departure schedule
     * @param seat The seat to book
     * @param bus The bus on which booking is to be made
     * @return true if the booking is successful, false otherwise
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat,  Bus bus) {
        return makeBooking(departureSchedule, List.of(seat), bus);
    }
    /**
     * Retrieves the bus ID associated with this payment.
     * @return The bus ID
     */
    public int getBusId() {
        return this.busId;
    }
    /**
     * Returns a string representation of the departure information including date, IDs, and booked seats.
     * @return A string representation of the departure information
     */
    public String getDepartureInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "\nDeparture Date: " + dateFormat.format(departureDate.getTime()) + "\nID : " + this.id + "\nBuyer ID : "
                + super.buyerId + "\nRenter ID : " + super.renterId + "\nBus ID : " + this.busId
                + "\nDeparture Date : " + this.departureDate + "\nBus Seat : " + this.busSeats;
    }
    /**
     * Returns a string representation of the departure information including date, IDs, and booked seats.
     * @return A string representation of the departure information
     */
    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return timeFormat.format(departureDate.getTime());
    }
}
