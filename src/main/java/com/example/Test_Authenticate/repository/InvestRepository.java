package com.example.Test_Authenticate.repository;

import com.example.Test_Authenticate.DTO.FundInvest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvestRepository extends CrudRepository<FundInvest, Long> {


}
