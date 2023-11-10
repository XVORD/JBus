package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

import java.util.regex.*;
public class Account extends Serializable {
    public String email;
    public String name;
    public String password;
    public Renter company ;
    public double balance;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public Account(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
    }

    public String toString() {
        return "Id :" + this.id + "\nName :" + this.name + "\nEmail :" + this.email + "\nPassword :" + this.password;
    }

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
}
