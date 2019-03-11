package com.example.Test_Authenticate.service;


import com.example.Test_Authenticate.DTO.FundItem;
import com.example.Test_Authenticate.repository.FundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FundService {

    @Autowired
    FundRepository repo;

    public FundItem get(Long id){
        return repo.findById(id).get();
    }

    public FundItem create(FundItem item){
        //기타 비지니스 처리가 필요하지만 생략
        return repo.save(item);
    }

    //update

}
