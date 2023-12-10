package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

import java.util.regex.*;

/**
 * Account Class
 * Contains common account attributes such as name, email, password, company, and balance.
 * Has basic method of checking the eligibility of email and password.
 * @author Christopher Satya
 */
public class Account extends Serializable {
    public String email; //Email used to register the account.
    public String name; //Name used to register the account.
    public String password; // Password used to register the account.
    public Renter company ; // Company used to register the renter account.
    public double balance; //Current balance of the account.
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$"; //REGEX used to verify eligibility of email. Checks for Alphanumeric character before '@'. Alphabets after '@' seperated with a '.' without another '.' at the last character.
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"; //REGEX used to verify eligibility of password. Checks if password is at least 8 characters long, has atleast 1 uppercase and 1 lowercase alphabet, and atleast 1 number.

    /**
     * Constructor of a newly registered account.
     * @param name Newly registered name.
     * @param email Newly registered email that passes the REGEX.
     * @param password Newly registered password that passes the REGEX.
     */
    public Account(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
    }

    /**
     * Method to convert the account's attributes to a printable java.lang.String
     * @return Returns a string of the account's attributes.
     */
    public String toString() {
        return "Id :" + this.id + "\nName :" + this.name + "\nEmail :" + this.email + "\nPassword :" + this.password;
    }
    /**
     * Method used to check the eligibility of the account's email and password.
     * @return Returns if the account's email and password is eligible according to the REGEX.
     */
    public boolean validate() {
        Pattern PatternEmail = Pattern.compile(REGEX_EMAIL);
        Pattern PatternPassword = Pattern.compile(REGEX_PASSWORD);
        Matcher MatcherEmail = PatternEmail.matcher(email);
        Matcher MatcherPassword = PatternPassword.matcher(password);
        if (MatcherEmail.find() && MatcherPassword.find()) {
            return true;
        }
        return false;
    }

    /**
     * Increases the account's balance by the specified amount if the amount is greater than 0.
     * @param amount The amount to be added to the account's balance.
     * @return `true` if the top-up is successful, `false` if the amount is not greater than 0.
     */
    public boolean topUp(double amount){
        if (amount <= 0){
            return false;
        }
        this.balance += amount;
        return true;
    }
}
