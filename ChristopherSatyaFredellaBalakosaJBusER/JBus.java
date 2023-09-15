package ChristopherSatyaFredellaBalakosaJBusER;

public class JBus {
    public static void main(String[] args) {
        Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
    }

    public static int getBusId() {
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
    public static Bus createBus(){
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
    }
}
