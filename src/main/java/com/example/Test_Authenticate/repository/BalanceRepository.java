package com.example.Test_Authenticate.repository;

import com.example.Test_Authenticate.DTO.Balance;
import com.example.Test_Authenticate.DTO.BalanceHistory;
import org.springframework.data.repository.CrudRepository;

public interface BalanceRepository extends CrudRepository<Balance, Long> {
}


