package ChristopherSatyaFredellaBalakosaJBusER;

public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    public Station(int id, String stationName, City city, String address){
        super(id);
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
    public String toString(){
        return "\nID : " + super.id + "\nStation Name : " + this.stationName + "\nCity : " + this.city + "\nAddress :" + this.address;
    }
}