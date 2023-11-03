package ChristopherSatyaFredellaBalakosaJBusER;

public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    
    public Account(String name, String email, String password){
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String toString(){
        return "Id :" + this.id + "\nName :" + this.name + "\nEmail :" + this.email + "\nPassword :" + this.password;
    }
}
