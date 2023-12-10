package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.Station;
import com.ChristopherSatyaFredellaBalakosaJBusER.Bus;
import com.ChristopherSatyaFredellaBalakosaJBusER.City;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonAutowired;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * StationController class
 * Handles HTTP requests related to stations and provides functionality
 * for creating stations and retrieving station information.
 * @author Christopher Satya
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    /**
     * The JSON database table for storing `Station` objects.
     */
    public static @JsonAutowired(value = Station.class, filepath = "data/station.json")
    JsonTable<Station> stationTable;
    /**
     * Retrieves the JSON database table associated with this controller.
     * @return The JSON table for `Station` objects.
     */
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }

    /**
     * Handles the HTTP POST request to create a new station. This method allows users to add a new
     * station with the specified details, including the station name, city, and address.
     * @param stationName The name of the new station.
     * @param city        The city where the station is located (must be a valid enum value).
     * @param address     The address of the station.
     * @return A `BaseResponse` object indicating the result of the station creation operation.
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            // Validate city as a valid enum value
            City cityEnum = City.valueOf(city.toUpperCase());

            // Create a new station using the provided details
            Station newStation = new Station(stationName, cityEnum, address);

            // Add the new station to the stationTable
            stationTable.add(newStation);

            //Success response message
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }
    /**
     * Handles the HTTP GET request to retrieve a list of all existing stations.
     * @return A list of `Station` objects representing all available stations.
     */
    @GetMapping("/getAll")
    public List<Station> getAllStation() { return getJsonTable();}


}
