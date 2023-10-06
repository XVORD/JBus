package ChristopherSatyaFredellaBalakosaJBusER;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter( String companyName, int phonenumber){
        super();
        this.address = " ";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    public Renter( String companyName, int phonenumber, String address){
        super();
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    public Renter( String companyName){
        super();
        this.address = " ";
        this.companyName = companyName;
        this.phoneNumber = 0;
    }
    public Renter( String companyName, String address){
        super();
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = 0;
    }
}
