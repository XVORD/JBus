package com.ChristopherSatyaFredellaBalakosaJBusER;

import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;
/**
 * Station Class
 * Represents a station in the transportation network. This class holds information
 * about a specific station, including its name, the city it is located in, and its address.
 * @author Christopher Satya
 */
public class Station extends Serializable
{
    public City city;           // The city in which the station is located
    public String stationName;  // The name of the station
    public String address;      // The address of the station

    /**
     * Constructs a new Station object with the specified name, city, and address.
     * @param stationName The name of the station.
     * @param city        The city where the station is located.
     * @param address     The address of the station.
     */
    public Station(String stationName, City city, String address){
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
    /**
     * Returns a string representation of the Station object.
     * The string includes the station's ID, name, city, and address.
     * @return A string representation of the Station object.
     */
    public String toString(){
        return "\nID : " + super.id + "\nStation Name : " + this.stationName + "\nCity : " + this.city + "\nAddress :" + this.address;
    }
}
