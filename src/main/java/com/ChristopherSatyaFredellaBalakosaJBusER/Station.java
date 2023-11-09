package com.ChristopherSatyaFredellaBalakosaJBusER;

public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    public Station(String stationName, City city, String address){
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
    public String toString(){
        return "\nID : " + super.id + "\nStation Name : " + this.stationName + "\nCity : " + this.city + "\nAddress :" + this.address;
    }
}
