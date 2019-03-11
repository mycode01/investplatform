package com.example.Test_Authenticate.repository;

import com.example.Test_Authenticate.DTO.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    public Member findByUemail(String uemail);

    public Member findByUid(String uid);

    @Override
    public List<Member> findAll();


}
