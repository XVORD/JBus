package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.*;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonAutowired;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
/**
 * BusController class
 * Handles HTTP requests related to buses and provides functionality for creating buses, adding schedules, and retrieving bus information.
 * @author Christopher Satya
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    /**
     * The JSON database table for storing `Bus` objects.
     */
    @JsonAutowired(value = Bus.class, filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\bus.json")
    public static JsonTable<Bus> busTable;
    /**
     * Retrieves the JSON database table associated with this controller.
     * @return The JSON table for `Bus` objects.
     */
    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }
    /**
     * Handles the HTTP POST request to create a new bus. This method allows users to add a new bus with the specified details.
     * @param accountId         The ID of the account associated with the bus.
     * @param name              The name of the bus.
     * @param capacity          The seating capacity of the bus.
     * @param facilities        The list of facilities available on the bus.
     * @param busType           The type of the bus (e.g., Standard, Deluxe).
     * @param price             The price of the bus ticket.
     * @param stationDepartureId The ID of the departure station.
     * @param stationArrivalId   The ID of the arrival station.
     * @return A `BaseResponse` object indicating the result of the bus creation operation.
     */
    @PostMapping("/create")
    public BaseResponse<Bus> addBus(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ){
        try{
            Account account = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == accountId);
            if (account == null || account.company == null) {
                return new BaseResponse<>(false, "Account tidak ada", null);
            }
            Station stationDep = Algorithm.<Station>find(StationController.stationTable, stat -> stat.id == stationDepartureId);
            Station stationArr = Algorithm.<Station>find(StationController.stationTable, stat -> stat.id == stationArrivalId);
            if(stationDep == null || stationArr == null){
                return new BaseResponse<>(false, "Kota keberangkatan/kedatangan tidak ada ", null);
            }

            Bus newbus = new Bus(
                    accountId,
                    name,
                    facilities,
                    new Price(price),
                    capacity,
                    busType,
                    stationDep,
                    stationArr
            );
            busTable.add(newbus);
            busTable.writeJson();
            return new BaseResponse<>(true, "Bus baru telah didaftarkan", newbus);}
        catch (IllegalArgumentException | IOException e){
            return new BaseResponse<>(false, "Bus gagal dibuat",null);
        }
    }
    /**
     * Handles the HTTP POST request to add a new schedule for a bus. This method allows users to add a new schedule for the specified bus.
     * @param busId The ID of the bus for which to add a schedule.
     * @param time  The timestamp representing the schedule time.
     * @return A `BaseResponse` object indicating the result of the schedule addition operation.
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        Bus bus = Algorithm.<Bus>find(busTable, x -> x.id == busId);
        bus.addSchedule(Timestamp.valueOf(time));
        return new BaseResponse<>(true, "Schedule Berhasil ditambahkan", bus);
    }
    /**
     * Handles the HTTP GET request to retrieve a list of buses associated with a specific account.
     * @param accountId The ID of the account for which to retrieve buses.
     * @return A list of `Bus` objects associated with the specified account.
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(),
                b->b.accountId==accountId);}
    /**
     * Handles the HTTP GET request to retrieve a list of all available buses.
     * @return A list of all available `Bus` objects.
     */
    @GetMapping("/getAllBus")
    public List<Bus> getAllBus(){
        return getJsonTable();
    }
}
