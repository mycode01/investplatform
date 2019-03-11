package com.example.Test_Authenticate.controller;


import com.example.Test_Authenticate.DTO.Phone;
import com.example.Test_Authenticate.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    MemberService service;

    @GetMapping("")
    @ResponseBody
    public List<Phone> get(@AuthenticationPrincipal User user) {
        return service.get(user.getUsername()).getPhones();
    }

    @PostMapping("")
    @ResponseBody
    public List<Phone> create(@AuthenticationPrincipal User user, @RequestBody Phone p) throws Exception {
        return service.addPhone(service.get(user.getUsername()), p).getPhones();
    }

    @PutMapping("")
    @ResponseBody
    public List<Phone> update(@AuthenticationPrincipal User user, @RequestBody Phone p) {
        return service.updatePhone(service.get(user.getUsername()), p).getPhones();
    }
}
