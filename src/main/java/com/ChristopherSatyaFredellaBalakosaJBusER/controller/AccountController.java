package com.ChristopherSatyaFredellaBalakosaJBusER.controller;

import com.ChristopherSatyaFredellaBalakosaJBusER.Account;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonAutowired;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonTable;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class, filepath = "C:\\Kuliah\\Java Prak\\JBus\\data\\accountDatabase.json")
    public static JsonTable<Account> accountTable;
    @GetMapping
    String index() { return "account page"; }

    @Override
    public JsonTable<Account> getJsonTable() {
        return null;
    }
    @PostMapping("/register")
    BaseResponse <Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        return new Account(name, email, password);
    }

    BaseRespones<Account> login(
            @RequestParam String email,
            @RequestParam String passwrod
    )
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
