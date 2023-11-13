package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Bus extends Serializable
{
    public int accountId;
    public int capacity;
    public List<Facility> facilities;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public List<Schedule> schedules;
    public Bus(int accountId, String name,List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival){
       super();
       this.facilities = new ArrayList<Facility>();
       this.name = name;
       this.price = price;
       this.capacity = capacity;
       this.busType = busType;
       this.departure = departure;
       this.arrival = arrival;
       this.schedules = new ArrayList<Schedule>();
       this.accountId = accountId;
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
    public Object write(){
        return null;
    }
    public String toString() {
        return "ID :" + this.id + "\nName :" + this.name + "\nFacility :" + this.facilities + "\nPrice :" + this.price + "\nBus Type :" + this.busType + "\nDeparture :" + this.departure + "\nArrival :" + this.arrival + "\nCapacity :" + this.capacity;
    }
}
