package ChristopherSatyaFredellaBalakosaJBusER;

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
    
    public Bus(int id, String name,Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
       super(id);
       this.facility = facility;
       this.name = name;
       this.price = price;
       this.capacity = capacity;
       this.busType = busType;
       this.city = city;
       this.departure = departure;
       this.arrival = arrival;
    }
    public String toString(){
        return "ID :" + this.id + "\nName :" + this.name + "\nFacility :" + this.facility + "\nPrice :" + this.price + "\nBus Type :" + this.busType + "\nCity :" + this.city + "\nDeparture :" + this.departure + "\nArrival :" + this.arrival + "\nCapacity :" + this.capacity;
    }
}
