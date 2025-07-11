package com.example.sample.controller;

import com.example.sample.entity.AccountEntity;
import com.example.sample.service.AccountService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("showAccountView")
public class AccountViewController {
    @Autowired
    AccountService accountService;

    @GetMapping("/sample")
    public String sample(Model model) {
        List<AccountEntity> accountEntityList = accountService.getAccount();
        model.addAttribute("accountEntityList", accountEntityList);
        return "sample";
    }
}
