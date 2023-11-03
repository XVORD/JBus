package ChristopherSatyaFredellaBalakosaJBusER;
import java.util.regex.*;
public class Renter extends Serializable {
    public String address;
    public String companyName;
    public int phoneNumber;
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{3,19}$";
    private final String REGEX_PHONE = "^[0-9]{9,12}$";

    public Renter(String companyName, int phonenumber) {
        super();
        this.address = " ";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public Renter(String companyName, int phonenumber, String address) {
        super();
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public Renter(String companyName) {
        super();
        this.address = " ";
        this.companyName = companyName;
        this.phoneNumber = 0;
    }

    public Renter(String companyName, String address) {
        super();
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = 0;
    }

    public boolean validate() {
        Pattern patternphone = Pattern.compile(REGEX_PHONE);
        Pattern patternname = Pattern.compile(REGEX_NAME);
        Matcher matcherphone = patternphone.matcher(Integer.toString(phoneNumber));
        Matcher matchername = patternname.matcher(companyName);
        if (matcherphone.find() && matchername.find()) {
            return true;
        }
        return false;
    }
}
