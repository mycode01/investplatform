package com.example.Test_Authenticate.controller;

import com.example.Test_Authenticate.DO.DInvestCreate;
import com.example.Test_Authenticate.DTO.FundInvest;
import com.example.Test_Authenticate.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/invest")
public class InvestController {

    @Autowired
    InvestService service;

    @GetMapping("")
    @ResponseBody
    public List<FundInvest> get(@AuthenticationPrincipal User user) {
        return service.getAll(user.getUsername());
    }

    @PostMapping("")
    @ResponseBody
    public DInvestCreate create(@AuthenticationPrincipal User user,
                                @RequestBody @Valid DInvestCreate invest) throws Exception{
        return DInvestCreate.asResponse(service.create(invest.fundItemId, invest.investamount, user.getUsername()));
    }
}
