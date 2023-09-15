package ChristopherSatyaFredellaBalakosaJBusER;

public class Bus
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Bus(String name,Facility facility, Price price, int capacity){
       this.facility = facility;
       this.name = name;
       this.price = price;
       this.capacity = capacity;
    }
}
