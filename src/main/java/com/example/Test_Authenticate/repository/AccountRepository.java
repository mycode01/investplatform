package com.example.Test_Authenticate.repository;

import com.example.Test_Authenticate.DTO.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long>{ }