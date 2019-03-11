package com.example.Test_Authenticate.service;

import com.example.Test_Authenticate.DTO.Account;
import com.example.Test_Authenticate.DTO.Balance;
import com.example.Test_Authenticate.DTO.Member;
import com.example.Test_Authenticate.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository repo;

    @Autowired
    MemberService memberService;

    @Autowired
    BalanceService balanceService;

    public Account get(long id) {
        return repo.findById(id).get();
    }

    public Account createAccount(String uid) throws Exception {
        Member member = memberService.get(uid);
        if (member.getAccount() != null) {
            throw new Exception("has account already");
        }

        Account account = new Account();

        account.setUsername(member.getUname());
        account.setApproved("Y");

        account.setBalance(new Balance());

        return repo.save(account);
    }
}
