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

    @Override
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
        Predicate<Account> s = (valid) -> valid.email.equals(email);
        String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
        String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

        Pattern patternEmail = Pattern.compile("REGEX_EMAIL");
        Pattern patternPassword = Pattern.compile("REGEX_PASSWORD");

        Matcher matcherPassword = patternPassword.matcher(password);
        Matcher matcherEmail = patternEmail.matcher((email));

        if (!name.isBlank() && matcherPassword.find() && matcherEmail.find() && !Algorithm.exists(accountTable, s) == false) {
            String passwordToHash = password;
            String generatedPassword = null;

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");

                md.update(passwordToHash.getBytes());

                byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Account temp = new Account(name, email, generatedPassword);
            accountTable.addElement(temp);
            return new BaseResponse<>(true, "Berhasil Register", temp);
        }
        return new BaseResponse<>(false, "Gagal Register",null);
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
            for (int i = 0;i < bytes.length;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
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
