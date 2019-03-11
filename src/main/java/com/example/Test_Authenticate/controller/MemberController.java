package com.example.Test_Authenticate.controller;

import com.example.Test_Authenticate.DTO.AuthenticationRequest;
import com.example.Test_Authenticate.DTO.AuthenticationToken;
import com.example.Test_Authenticate.DTO.Member;
import com.example.Test_Authenticate.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService service;

    @PostMapping("/createUser")
    @ResponseBody
    public Member create(Member member){
        return service.create(member, false);
    }

    @PostMapping("/createAdmin")
    @ResponseBody
    public Member createAdmin(Member member){
        return service.create(member, true);
    }
    @GetMapping("/id/{uid}")
    @ResponseBody
    public String getId(@PathVariable String uid){
        return service.get(uid).toString();
    }

    @GetMapping("/id")
    public List<Member> getAllId(){
        return service.list();
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login") // oauth의 경우 가볍게 테스트가 불가능하니 제외함
    @ResponseBody
    public AuthenticationToken login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpSession session
    )
    { // 로그인시 시큐리티에서 제공중인 userdetails.User를 extends 하여 다른 데이터를 담을수있지만
        // 세션이 공유되지 않는 다른서버를 이용시 데이터의 일관성이 문제가 될수있기때문에
        // 그냥 유저 id를 unique걸고 사용시마다 id를 검색하게 함
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return new AuthenticationToken(authentication.getName(), authentication.getAuthorities(), session.getId());

    }


}
