package com.example.Test_Authenticate.controller;

import com.example.Test_Authenticate.DTO.Account;
import com.example.Test_Authenticate.DTO.Balance;
import com.example.Test_Authenticate.DTO.BalanceHistory;
import com.example.Test_Authenticate.service.AccountService;
import com.example.Test_Authenticate.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    BalanceService balanceService;

    @GetMapping("/balance")
    @ResponseBody
    public Balance balance(@AuthenticationPrincipal User user) throws Exception{
        return balanceService.getBalance(user.getUsername());
    }

    @GetMapping("/history")
    @ResponseBody
    public List<BalanceHistory> hisotory(@AuthenticationPrincipal User user) throws Exception{
        return balanceService.getHistory(user.getUsername());

    }

    @PostMapping("")
    @ResponseBody // create account
    public Account create(@AuthenticationPrincipal User user) throws Exception{
        return accountService.createAccount(user.getUsername());
    }


    @PostMapping("/deposit")
    @ResponseBody //테스트용 입금,
    public Balance deposit(@AuthenticationPrincipal User user) {
        return balanceService.deposit(user.getUsername());
    }

}
