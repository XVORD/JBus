package com.ChristopherSatyaFredellaBalakosaJBusER;

import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;

public class Voucher extends Serializable {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(String name, int code, Type type, double minimum, double cut) {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
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
    public boolean isUsed() {
        return this.used;    
    }
    public boolean canApply(Price price) {
        if (price.price > this.minimum && !this.used) {
            return true;
        } else {
            return false;
        }
    }
}
