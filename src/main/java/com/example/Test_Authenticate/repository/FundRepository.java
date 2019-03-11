package com.example.Test_Authenticate.repository;

import com.example.Test_Authenticate.DTO.FundItem;
import org.springframework.data.repository.CrudRepository;

public interface FundRepository extends CrudRepository<FundItem, Long> {
}
