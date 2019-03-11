package com.example.Test_Authenticate.controller;

import com.example.Test_Authenticate.DO.DFundCreate;
import com.example.Test_Authenticate.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/fund")
public class FundController {

    @Autowired
    FundService service;

    @GetMapping("/{fid}")
    @ResponseBody
    public DFundCreate get(@PathVariable Long fid) {
        return DFundCreate.asResponse(service.get(fid));
    }

    @PostMapping("")
    @ResponseBody
    public DFundCreate create(@RequestBody @Valid DFundCreate dFund) {
        return DFundCreate.asResponse(service.create(dFund.toFundItem()));
    }


}
