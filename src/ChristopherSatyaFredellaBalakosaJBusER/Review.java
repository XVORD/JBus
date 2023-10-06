package ChristopherSatyaFredellaBalakosaJBusER;

public class Review extends Serializable
{
    public String date;
    public String desc;
    public Review(String date, String desc){
        super();
        this.date = date;
        this.desc = desc;
    }
    public String toString(){
        return "Date :" + this.date + "\nDesc :" + this.desc;
    }
}
