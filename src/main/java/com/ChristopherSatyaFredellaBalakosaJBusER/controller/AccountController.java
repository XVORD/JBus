package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.Account;
import com.ChristopherSatyaFredellaBalakosaJBusER.Algorithm;
import com.ChristopherSatyaFredellaBalakosaJBusER.Predicate;
import com.ChristopherSatyaFredellaBalakosaJBusER.Renter;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonAutowired;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Account Controller Class
 * Contains method related to Account, such as login, register, etc.
 * @author Christopher Satya
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    /**
     * The JSON database table for storing `Account` objects.
     */
    @JsonAutowired(value = Account.class, filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\accountDatabase.json")
    public static JsonTable<Account> accountTable;

    /**
     * Handles the HTTP GET request for the root path ("/account") and returns a simple message.
     * @return A message indicating that it's the account page.
     */
    @GetMapping
    String index() {
        return "account page";
    }
    /**
     * Retrieves the JSON database table associated with this controller.
     * @return The JSON table for `Account` objects.
     */
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }
    /*
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }

     */
    /**
     * Method handles HTTP POST requests to "/account/register" and allows users to
     * register by providing their name, email, and password as request parameters.
     * @param name The name of the user.
     * @param email The email address of the user.
     * @param password The password chosen by the user.
     * @return A `BaseResponse` object indicating the result of the registration.
     */
    @PostMapping("/register")
    BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        if(name.isBlank())
            return new BaseResponse<>(false, "Gagal register ", null);

        Account account = new Account(name, email, password);
        if(!account.validate())
            return new BaseResponse<>(false, "Gagal register", null);
        else {
            String generatedPass = null;
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte byteTemp : bytes){
                    sb.append(String.format("%02x", byteTemp & 0xff));
                }
                generatedPass = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            ArrayList<String> list = new ArrayList<String>();
            for (Account a : accountTable){
                list.add(a.email);
            }
            if(!Algorithm.exists(list, email)) {
                account.name = name; account.email = email; account.password = generatedPass;
                accountTable.add(account);
                return new BaseResponse<>(true, "Berhasil register", account);
            } else
                return new BaseResponse<>(false, "Gagal register ", null);
        }
    }
    /**
     * Method handles HTTP POST requests to "/account/login" and allows users to log in by
     * providing their email and password as request parameters.
     * @param email    The email address of the user.
     * @param password The password provided by the user.
     * @return A `BaseResponse` object indicating the result of the login attempt.
     */
    @PostMapping("/login")
    BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        String passwordToHash = password;
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteTemp : bytes){
                sb.append(String.format("%02x", byteTemp & 0xff));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for (Account i : accountTable) {
            if (i.password.equals(generatedPassword) && i.email.equals(email))
                return new BaseResponse<>(true, "Berhasil Login", i);
        }
        return new BaseResponse<>(false, "Gagal Login", null);
    }
    /**
     * Method handles HTTP POST requests to "/account/{id}/registerRenter" and allows
     * users with a specific ID to register as renters by providing company name, address, and phone number as request parameters.
     * @param id          The ID of the user who wants to register as a renter.
     * @param companyName The name of the renter's company.
     * @param address     The address of the renter's company.
     * @param phoneNumber The phone number of the renter's company.
     * @return A `BaseResponse` object indicating the result of the renter registration.
     */
    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ) {
        for (Account i : accountTable) {
            if (i.id == id && i.company == null){
                i.company = new Renter(companyName, phoneNumber, address);
                return new BaseResponse<>(true, "Berhasil Register Renter", i.company);
            }
        }
        return new BaseResponse<>(false, "Gagal Register Renter", null);
    }
    /**
     * Method handles HTTP POST requests to "/account/{id}/topUp" and allows users with a
     * specific ID to top up their account balance by providing an amount as a request parameter.
     * @param id     The ID of the user who wants to top up their account.
     * @param amount The amount to be added to the user's account balance.
     * @return A `BaseResponse` object indicating the result of the top-up operation.
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam double amount
    ) {
        for (Account i : accountTable) {
            if (i.id == id){
                if (i.topUp(amount)){
                    return new BaseResponse<>(true, "Berhasil Top Up", amount);
                }
            }
        }
        return new BaseResponse<>(false, "Gagal Top Up", null);
    }
}
