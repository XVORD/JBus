package ChristopherSatyaFredellaBalakosaJBusER;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter(int id, String companyName, int phonenumber){
        super(id);
        this.address = " ";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    public Renter(int id, String companyName, int phonenumber, String address){
        super(id);
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    public Renter(int id, String companyName){
        super(id);
        this.address = " ";
        this.companyName = companyName;
        this.phoneNumber = 0;
    }
    public Renter(int id, String companyName, String address){
        super(id);
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = 0;
    }
}
