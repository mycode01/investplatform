package com.example.Test_Authenticate.repository;

import com.example.Test_Authenticate.DTO.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhoneRepository extends CrudRepository<Phone, Long> { // 실제론 사용하지 않지만 테스트용으로 생성
    @Override
    public List<Phone> findAll();
}
