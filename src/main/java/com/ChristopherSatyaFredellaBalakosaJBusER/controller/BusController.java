package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.*;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonAutowired;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {

    @JsonAutowired(value = Bus.class, filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\bus.json")
    public static JsonTable<Bus> busTable;

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ){
        if(Algorithm.<Bus>exists(busTable, x -> x.arrival.id == stationArrivalId) && Algorithm.<Bus>exists(busTable, x -> x.departure.id == stationDepartureId)) {
            Account account = Algorithm.<Account>find(AccountController.accountTable, x -> x.id == accountId);
            if (account != null) {
                if (account.company != null) {
                    Bus bus = Algorithm.<Bus>find(busTable, x -> x.arrival.id == stationArrivalId && x.departure.id == stationDepartureId);
                    Price priceObj = new Price(price);
                    Bus createdBus = new Bus(accountId, name, facilities, priceObj, capacity, busType, bus.departure, bus.arrival);
                    return new BaseResponse<>(true, "Bus Berhasil dibuat", createdBus);
                } else
                    return new BaseResponse<>(false, "Gagal, Account bukan Renter", null);
            }
            return new BaseResponse<>(false, "Gagal Menemukan Account dengan ID yang ada", null);
        } return new BaseResponse<>(false, "Gagal, tidaka ada ID Departure atau Arrival di database", null);
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        try{
        Bus bus = Algorithm.<Bus>find(busTable, x -> x.id == busId);
        if (bus != null) {
            bus.addSchedule(Timestamp.valueOf(time));

            return new BaseResponse<>(true, "Schedule berhasil ditambahkan", bus);
        } else {
            return new BaseResponse<>(false, "Gagal, Bus dengan ID " + busId + " tidak ditemukan", null);
        }
    } catch (Exception e) {
        return new BaseResponse<>(false, "Gagal menambahkan schedule. Error: " + e.getMessage(), null);
    }
    }
}
