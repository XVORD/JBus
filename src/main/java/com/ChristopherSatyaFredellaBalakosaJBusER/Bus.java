package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;
    public Bus( String name,Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
       super();
       this.facility = facility;
       this.name = name;
       this.price = price;
       this.capacity = capacity;
       this.busType = busType;
       this.city = city;
       this.departure = departure;
       this.arrival = arrival;
       this.schedules = new ArrayList<Schedule>();
    }
    public void addSchedule(Timestamp schedule){
        boolean isDuplicate = false;
        for (Schedule existingSchedule : schedules) {
            if (existingSchedule.departureSchedule.equals(schedule)) {
                isDuplicate = true;
                break;
            }
        }
        if (!isDuplicate) {
            schedules.add(new Schedule(schedule, this.capacity));
            System.out.println("Jadwal berhasil ditambahkan.");
        } else {
            System.out.println("Terdapat jadwal yang terduplikasi, jadwal tidak ditambahkan.");
        }
    }
    /*public void printSchedule(Schedule schedule){
        System.out.println("Daftar kursi dan ketersediaan kursi :");
        for(String seatNumber : schedule.seatAvailability.keySet()){
            System.out.println(seatNumber + ":" + schedule.seatAvailability.get(seatNumber));
        }
    }*/
    public String toString() {
        return "ID :" + this.id + "\nName :" + this.name + "\nFacility :" + this.facility + "\nPrice :" + this.price + "\nBus Type :" + this.busType + "\nCity :" + this.city + "\nDeparture :" + this.departure + "\nArrival :" + this.arrival + "\nCapacity :" + this.capacity;
    }
}
