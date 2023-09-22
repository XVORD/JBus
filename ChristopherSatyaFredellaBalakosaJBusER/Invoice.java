package ChristopherSatyaFredellaBalakosaJBusER;

public class Invoice extends Serializable
{
    public String time;
    public int buyerId;
    public int renterId;
    protected Invoice(int id, int buyerId, int renterId, String time){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.time = time;
    }
    public String print(){
        return "\nID : " + super.id + "\nTime : " + this.time + "\nBuyer ID : " + this.buyerId + "\nRenter ID : " + this.renterId;
    }
}
