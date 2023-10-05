package ChristopherSatyaFredellaBalakosaJBusER;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Payment extends Invoice {
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis());
    }
    public static boolean isAvailable(Timestamp departureSchedule, String seat,  Bus bus) {
        for(Schedule s : bus.schedules){
            if(s.isSeatAvailable(seat) == true){
                return true;
            }
        }
        
        return false;    
    }

    public static boolean makeBooking(Timestamp departureSchedule, String seat,  Bus bus) {
        if (isAvailable(departureSchedule, seat, bus)) {
            for(Schedule schedule : bus.schedules){
                if(schedule.departureSchedule.equals(departureSchedule)){
            schedule.bookSeat(seat);
            return true;
                }
            }
        }
        return false;
    }
    public int getBusId() {
        return this.busId;
    }
    public String getDepartureInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "\nDeparture Date: " + dateFormat.format(departureDate.getTime()) + "\nID : " + this.id + "\nBuyer ID : "
                + super.buyerId + "\nRenter ID : " + super.renterId + "\nBus ID : " + this.busId
                + "\nDeparture Date : " + this.departureDate + "\nBus Seat : " + this.busSeat;
    }
    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return timeFormat.format(departureDate.getTime());
    }
}
