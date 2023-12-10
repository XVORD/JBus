package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

import java.util.regex.*;
/**
 * Renter Class
 * Represents a renter with a company name, phone number, and address.
 * This class includes methods for validating the renter's information against specific patterns.
 * @author Christopher Satya
 */
public class Renter extends Serializable {
    public String address;       // The address of the renter
    public String companyName;   // The name of the company associated with the renter
    public String phoneNumber;   // The phone number of the renter
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{3,19}$";  // Pattern for validating the company name
    private final String REGEX_PHONE = "^[0-9]{9,12}$";             // Pattern for validating the phone number

    /**
     * Constructs a new Renter with the specified company name and phone number.
     * Initializes address with an empty string.
     * @param companyName The name of the company associated with the renter.
     * @param phoneNumber The phone number of the renter.
     */

    public Renter(String companyName, String phoneNumber) {
        super();
        this.address ="";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    /**
     * Constructs a new Renter with the specified company name, phone number, and address.
     * @param companyName The name of the company associated with the renter.
     * @param phoneNumber The phone number of the renter.
     * @param address     The address of the renter.
     */
    public Renter(String companyName, String phoneNumber, String address) {
        super();
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    /**
     * Constructs a new Renter with the specified company name.
     * Initializes both address and phone number with empty strings.
     * @param companyName The name of the company associated with the renter.
     */
    public Renter(String companyName) {
        super();
        this.address ="";
        this.companyName = companyName;
        this.phoneNumber ="";
    }
    /**
     * Validates the renter's company name and phone number against predefined patterns.
     * @return true if both the company name and phone number match their respective patterns, false otherwise.
     */
    public boolean validate() {
        Pattern patternphone = Pattern.compile(REGEX_PHONE);
        Pattern patternname = Pattern.compile(REGEX_NAME);
        Matcher matcherphone = patternphone.matcher(phoneNumber);
        Matcher matchername = patternname.matcher(companyName);
        if (matcherphone.find() && matchername.find()) {
            return true;
        }
        return false;
    }
}
