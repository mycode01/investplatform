package com.example.Test_Authenticate.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthenticationRequest { //리퀘스트 도메인객체, 그냥 데이터를 편하게 받는 수단
    private String username;
    private String password;
}


