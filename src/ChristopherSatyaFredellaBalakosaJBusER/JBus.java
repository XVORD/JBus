package ChristopherSatyaFredellaBalakosaJBusER;
import java.sql.Timestamp;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
public class JBus {
    // TP Modul 6
    public static void main(String[] args) {
        // TP Modul 6
        String filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\station.json";
        Gson gson = new Gson();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filepath));
            List<Station> stationjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());
            stationjson.forEach(e -> System.out.println(e.toString()));
            System.out.println();
            buffer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    /*public static void main(String[] args) {
        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);
    }
    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val)->val<=22;
        Predicate<Integer> above = (val)->val>43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);
    }}
        /*Bus b = createBus();
        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
        
        b.addSchedule(schedule1);
        b.addSchedule(schedule2);
        b.schedules.forEach(Schedule::printSchedule);
        
        for (Schedule s:b.schedules){
            s.printSchedule();
        }
        //Invalid date
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat ER12");
        System.out.println(Payment.makeBooking(t1, "ER12", b));
        
        //Valid date, invalid seat
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER20");
        System.out.println(Payment.makeBooking(t2, "ER20", b));

        //Valid date, valid seat
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER07");
        System.out.println(Payment.makeBooking(t2, "ER07", b));
        
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01");
        System.out.println(Payment.makeBooking(t3, "ER01", b));

        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01 again");
        System.out.println(Payment.makeBooking(t3, "ER01", b));

        //Check if the data changes
        System.out.println("\nUpdated Schedule\n");
        b.schedules.forEach(Schedule::printSchedule);

    }


    /*public static int getBusId() {
        return 0;
    }

    public static String getBusName() {
        return "Bus";
    }

    public static boolean isDiscount() {
        return true;
    }

    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount) {
        float hasil;
        if (beforeDiscount <= afterDiscount) {
            hasil = 0.0f;
        } else {
            hasil = (beforeDiscount - afterDiscount) / 10.0f;
        }
        return hasil;
    }

    public static int getOriginalPrice(int discountedPrice, float discountPercentage) {
        int hasil;
        if (discountPercentage >= 100.0f) {
            hasil = 0;
        } else {
            hasil = (int)(discountedPrice / (1.0f - discountPercentage / 100));
        }
        return hasil;
    }

    public static int getDiscountedPrice(int price, float discountPercentage) {
        int hasil;
        if (discountPercentage > 100) {
            hasil = 0;
        } else {
            hasil = price * ((100-(int)discountPercentage) / 100);
        }
        return hasil;
    }

    public static float getAdminFeePercentage() {
        return 0.05f;
    }

    public static int getAdminFee(int price) {
        int hasil;
        hasil = price / 20;
        return hasil;
    }

    public static int getTotalPrice(int price, int numberOfSeat) {
        int hasil1;
        int hasil2;
        hasil1 = price * numberOfSeat;
        hasil2 = getAdminFee(price) * numberOfSeat;
        return hasil1 + hasil2;
    }
     public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
}*/
