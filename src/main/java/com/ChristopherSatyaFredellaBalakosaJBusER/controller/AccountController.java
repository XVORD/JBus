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

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    @JsonAutowired(value = Account.class, filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\accountDatabase.json")
    public static JsonTable<Account> accountTable;

    @GetMapping
    String index() {
        return "account page";
    }

    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }
    /*
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }

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
        return new BaseResponse<>(false, "Gagal Register", null);
    }

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
