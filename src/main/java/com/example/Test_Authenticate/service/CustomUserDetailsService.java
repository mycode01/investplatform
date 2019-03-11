package com.example.Test_Authenticate.service;

import com.example.Test_Authenticate.DTO.SecurityMember;
import com.example.Test_Authenticate.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    MemberRepository repo;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException{
            return Optional.ofNullable(repo.findByUid(uid)).filter(m->m!=null)
            .map(m->new SecurityMember(m)).get();
    }

}
