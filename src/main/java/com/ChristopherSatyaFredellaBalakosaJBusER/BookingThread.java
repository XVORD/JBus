package com.ChristopherSatyaFredellaBalakosaJBusER;
import java.sql.Timestamp;
/**
 * BookingThread Class
 * Represents a thread responsible for booking a seat on a bus.
 * @author Christopher Satya
 */
public class BookingThread extends Thread{
        private Bus bus;
        private Timestamp timestamp;
    /**
     * Constructor to create a BookingThread.
     * @param name The name of the thread.
     * @param bus The bus on which the booking will be made.
     * @param timestamp The timestamp for the booking.
     */
        public BookingThread(String name, Bus bus, Timestamp timestamp){
            super(name);
            this.bus = bus;
            this.timestamp = timestamp;
            this.start();
        }
    /**
     * Runs the booking thread.
     */
        public void run(){
        System.out.println("Thread " + Thread.currentThread().getName() + " ID : " + Thread.currentThread().getId() + " is running");
        synchronized(this.bus) {
            if (Payment.makeBooking(this.timestamp, "ER01", this.bus)){
                System.out.println("Thread " + Thread.currentThread().getId() + " Berhasil Melakukan Booking");
            } else
                System.out.println("Thread " + Thread.currentThread().getId() + " Gagal Melakukan Booking");
        }
    }
}
