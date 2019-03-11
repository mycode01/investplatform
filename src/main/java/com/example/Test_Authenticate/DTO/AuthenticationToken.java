package com.example.Test_Authenticate.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter

public class AuthenticationToken{ //클라이언트에게 돌려줄 토큰
    private String username;
    private Collection authorities;
    private String token;

    public AuthenticationToken(String username, Collection collection, String token){
        this.username = username;
        this.authorities = collection;
        this.token = token;
    }
}