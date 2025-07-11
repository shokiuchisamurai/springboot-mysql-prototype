package com.example.sample.controller;

import com.example.sample.entity.AccountEntity;
import com.example.sample.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("showAccountApi")
public class AccountApiController {
    @Autowired
    AccountService accountService;

    @GetMapping("/sample")
    public List<AccountEntity> sample() {
        return accountService.getAccount();
    }
}
