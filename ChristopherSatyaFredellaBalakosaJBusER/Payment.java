package ChristopherSatyaFredellaBalakosaJBusER;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate = Calendar.getInstance();
    public String busSeat;
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat) {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DATE, 2);
    }   
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat) {
        super(id, buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DATE, 2);
    }

    /*public String toString(){
        return "\nID : " + this.id + "\nBuyer ID : " + super.buyerId + "\nRenter ID : " + super.renterId + "\nTime : " + super.time + "\nBus ID : " + this.busId + "\nDeparture Date : " + this.departureDate + "\nBus Seat : " + this.busSeat;
    }*/
    public int getBusId(){
        return this.busId;
    }
    public String getDepartureInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("'Formatted Date: 'MM/dd/yyyy");
        return "\nDeparture Date: " + dateFormat.format(departureDate.getTime()) + "\nID : " + this.id + "\nBuyer ID : " + super.buyerId + "\nRenter ID : " + super.renterId + "\nBus ID : " + this.busId + "\nDeparture Date : " + this.departureDate + "\nBus Seat : " + this.busSeat ;
    }

    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return timeFormat.format(departureDate.getTime());
    }
}
