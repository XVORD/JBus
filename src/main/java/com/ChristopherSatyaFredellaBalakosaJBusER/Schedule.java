package com.ChristopherSatyaFredellaBalakosaJBusER;
import java.sql.Timestamp;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Schedule Class
 * Represents a schedule for a transportation service, containing details
 * about departure times and seat availability.
 * @author Christopher Satya
 */
public class Schedule {
    public Timestamp departureSchedule;           // The departure time of the schedule
    public Map<String, Boolean> seatAvailability; // Map representing seat availability

    /**
     * Constructs a new Schedule with the specified departure time and number of seats.
     * @param departureSchedule The departure time for the schedule.
     * @param numberOfSeats     The total number of seats available in this schedule.
     */

    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    /**
     * Initializes the seat availability for the schedule.
     *
     * @param numberOfSeats The number of seats to initialize.
     */
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<String, Boolean>();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0" +seatNumber : ""+seatNumber;
            seatAvailability.put("ER" + sn, true);
        }
    }
    /**
     * Checks if a specific seat is available.
     * @param seat The seat to check for availability.
     * @return true if the seat is available, false otherwise.
     */
    public boolean isSeatAvailable(String seat){
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }
    /**
     * Books a specific seat, marking it as unavailable.
     * @param seat The seat to be booked.
     */
    public void bookSeat(String seat){
        this.seatAvailability.put(seat, false);
    }
    /**
     * Books multiple seats, marking them as unavailable.
     * @param seats A list of seats to be booked.
     */
    public void bookSeat(List<String> seats){
        for (String seat : seats){
            bookSeat(seat);
        }
    }
    /**
     * Returns a string representation of the Schedule, including departure time,
     * number of occupied seats, and total seats.
     * @return A string representation of the Schedule.
     */
    @Override
    public String toString(){
        int seatTotal = seatAvailability.size();
        int seatOccupied = (int) seatAvailability.values().stream().filter(available -> !available).count();

        return "Schedule: " + departureSchedule +
                "\nOccupied " + seatOccupied +
                "\nTotal Seats: " + seatTotal;
    }
    /**
     * Checks if a list of seats are all available.
     * @param seats A list of seats to check for availability.
     * @return true if all seats in the list are available, false otherwise.
     */
    public boolean isSeatAvailable(List<String> seats){
        for (String seat : seats){
            if (!isSeatAvailable(seat)){
                return false;
            }
        }
        return true;
    }
    /**
     * Prints the schedule details, including departure time and seat availability.
     */
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        int maxSeatsPerRow = 4;
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat ++;
        }
        System.out.println("\n");
    }

}

