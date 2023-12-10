package com.ChristopherSatyaFredellaBalakosaJBusER;

import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;
/**
 * Voucher Class
 * The Voucher class represents a voucher that can be applied to a price for discounting purposes.
 * It supports both direct discount and percentage-based discount types.
 * Contains common voucher attributes
 * @author Christopher Satya
 */
public class Voucher extends Serializable {
    public String name; // Name of the voucher
    private boolean used; // Flag to indicate if the voucher has been used
    public double minimum; // Minimum price required to apply this voucher
    public double cut; // The discount amount or percentage
    public int code; // Code of the voucher
    public Type type; // Type of the voucher
    /**
     * Constructs a new Voucher with specified parameters.
     * @param name    The name of the voucher.
     * @param code    The unique code of the voucher.
     * @param type    The type of the voucher (either discount or direct cut).
     * @param minimum The minimum price required to apply this voucher.
     * @param cut     The discount amount or percentage.
     */
    public Voucher(String name, int code, Type type, double minimum, double cut) {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    /**
     * Applies the voucher to a given price.
     * The method updates the 'used' status of the voucher and calculates the discounted price.
     * @param price The price object to which the voucher will be applied.
     * @return The price after applying the voucher.
     */
    public double apply(Price price) {
        this.used = true;
        if (this.type == Type.DISCOUNT) {
            if (this.cut >= 100) {
                this.cut = 100;
                return 0;
            } else {
                return (price.price * ((100 - this.cut) / 100));
            }
        } else {
            if (price.price < this.cut) {
                return 0;
            } else {
                return (price.price - this.cut);
            }
        }
    }
    /**
     * Checks if the voucher has already been used.
     * @return true if the voucher has been used, false otherwise.
     */
    public boolean isUsed() {
        return this.used;    
    }
    /**
     * Determines whether the voucher can be applied to a given price.
     * @param price The price object to check against the voucher.
     * @return true if the voucher can be applied, false otherwise.
     */
    public boolean canApply(Price price) {
        if (price.price > this.minimum && !this.used) {
            return true;
        } else {
            return false;
        }
    }
}
