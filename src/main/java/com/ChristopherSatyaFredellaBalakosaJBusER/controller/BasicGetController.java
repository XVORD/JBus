package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.Algorithm;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * BasicGetController interface
 * Defines common methods for controllers that handle HTTP GET requests to retrieve data, such as retrieving data by ID or paginating results.
 * @author Christopher Satya
 */
public interface BasicGetController <T extends Serializable>{
    /**
     * Retrieves the `JsonTable` associated with this controller, which stores objects of type `T`.
     * @return The `JsonTable` containing objects of type `T`.
     */
    public JsonTable<T> getJsonTable();
    /**
     * Retrieves an object of type `T` by its unique ID.
     * @param id The unique identifier of the object to retrieve.
     * @return The object of type `T` with the specified ID, or `null` if not found.
     */
    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), e -> e.id == id);
    }
    /**
     * Retrieves a paginated list of objects of type `T` based on the requested page and page size.
     * @param page     The page number (starting from 0).
     * @param pageSize The number of items to include in each page.
     * @return A list of objects of type `T` representing the requested page of results.
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a-> true);
    }
}

