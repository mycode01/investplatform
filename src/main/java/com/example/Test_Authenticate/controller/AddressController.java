package com.example.Test_Authenticate.controller;

import com.example.Test_Authenticate.DTO.Address;
import com.example.Test_Authenticate.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    MemberService service;

    @GetMapping("")
    @ResponseBody
    public Address Get(@AuthenticationPrincipal User user) {
        return service.get(user.getUsername()).getAddress();
    }

    @PostMapping("")
    @ResponseBody
    public Address update(@AuthenticationPrincipal User user, @RequestBody Address address) {
        return service.updateAddress(service.get(user.getUsername()), address).getAddress();
    }
}
