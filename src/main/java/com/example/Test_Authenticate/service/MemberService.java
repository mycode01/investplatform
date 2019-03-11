package com.example.Test_Authenticate.service;

import com.example.Test_Authenticate.DTO.Address;
import com.example.Test_Authenticate.DTO.Member;
import com.example.Test_Authenticate.DTO.MemberRole;
import com.example.Test_Authenticate.DTO.Phone;
import com.example.Test_Authenticate.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {
    @Autowired
    MemberRepository repo;

    public Member create(Member member, boolean isAdmin){
        MemberRole role = new MemberRole();
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

        member.setUpw(enc.encode((member.getUpw())));

        role.setRoleName("BASIC");
        member.addRole(role);
        if(isAdmin) {
            role = new MemberRole();
            role.setRoleName("ADMIN");
            member.addRole(role);
        }

        return repo.save(member);
    }
    public Member get(String uid){
        return repo.findByUid(uid);
    }

    public Member get(Long id){
        return repo.findById(id).get();
    }

    public Page<Member> list(Pageable p){
        return repo.findAll(p);
    }

    public List<Member> list(){
        return repo.findAll();
    }

    public Member addPhone(Member member, Phone phone) throws Exception{ // service를 따로 만들고 로직에대한 vaildation을 진행하기 귀찮음
        if(member.getPhones().stream().filter(x->phone.getName().equals(x.getName())).count() > 0 ){
            throw new Exception("duplicated name"); //에러처리 역시
        }
        member.addPhone(phone);
        return repo.save(member);
    }

    public Member updatePhone(Member member, Phone phone){
        Phone target = member.getPhones().stream().filter(x-> phone.getName().equals(x.getName())).findAny().get();
        //없다면 에러날것
        target.setPno1(phone.getPno1());
        target.setPno2(phone.getPno2());
        target.setPno3(phone.getPno3());
        return repo.save(member);
    }

    public Member updateAddress(Member member, Address address){
        if(member.getAddress() == null)
            member.setAddress(address);
        else {
            member.getAddress().setType(address.getType());
            member.getAddress().setAddress1(address.getAddress1());
            member.getAddress().setAddress2(address.getAddress2());
        }
        return member;
    }

    // 릴레이션이 엮인 부분들을 각각 업데이트를 가져가기보다는 멤버에서 업데이트 할수있도록 함
    public Member update(Member member){
        return repo.save(member);
    }
}
