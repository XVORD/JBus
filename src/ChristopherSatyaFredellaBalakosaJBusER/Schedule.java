package ChristopherSatyaFredellaBalakosaJBusER;
import java.sql.Timestamp;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.*;

public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<String, Boolean>();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0" +seatNumber : ""+seatNumber;
            seatAvailability.put("ER" + sn, true);
        }
    }
    public boolean isSeatAvailable(String seat){
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }
    public void bookSeat(String seat){
        this.seatAvailability.put(seat, false);
    }
    public void bookSeat(List<String> seats){
        for (String seat : seats){
            bookSeat(seat);
        }
    }
    @Override
    public String toString(){
        int seatTotal = seatAvailability.size();
        int seatOccupied = (int) seatAvailability.values().stream().filter(available -> !available).count();

        return "Schedule: " + departureSchedule +
                "\nOccupied " + seatOccupied +
                "\nTotal Seats: " + seatTotal;
    }
    public boolean isSeatAvailable(List<String> seats){
        for (String seat : seats){
            if (!isSeatAvailable(seat)){
                return false;
            }
        }
        return true;
    }
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        int maxSeatsPerRow = 4;
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat ++;
        }
        System.out.println("\n");
    }

}

