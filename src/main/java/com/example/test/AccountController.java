package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {


    @Autowired
    AccountService accountService;


    @PostMapping("addaccount")
    public String addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @PutMapping("linkaccount")
    public String linkAccount(@RequestBody Map<String, Object> requestBody){
        List<Integer> ids = (List<Integer>) requestBody.get("ids");
        int accId = (int) requestBody.get("accId");
        return accountService.linkAccount(ids, accId);
    }

    @PutMapping("transcation")
    public String transactions(@RequestBody List<Extra> extras, @RequestParam int acc_id){
        return accountService.transactions(extras, acc_id);
    }




















}
