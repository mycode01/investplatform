package com.example.Test_Authenticate;


import com.example.Test_Authenticate.DTO.Address;
import com.example.Test_Authenticate.DTO.Member;
import com.example.Test_Authenticate.DTO.MemberRole;
import com.example.Test_Authenticate.DTO.Phone;
import com.example.Test_Authenticate.repository.MemberRepository;
import com.example.Test_Authenticate.repository.PhoneRepository;
import com.example.Test_Authenticate.service.MemberService;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository repo;

    @Test
    public void stage1_insertTest() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("김" + i + "번째");
            member.setUemail("nowhere@" + i);
            member.setIsActive("Y");
            MemberRole role = new MemberRole();
            if (i <= 80) {
                role.setRoleName("BASIC");
            } else if (i <= 90) {
                role.setRoleName("MANAGER");

            } else {
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));
            repo.save(member);
        }
    }

    @Test
    @Transactional
    public void stage2_memberPrintTest() {
        Optional result = repo.findById(85L);
        result.ifPresent(member -> log.info("member " + member));

    }

    @Autowired
    MemberService service;
    @Autowired
    PhoneRepository phoneRepository;

    @Test
    @Transactional
    public void stage3_addPhoneTest() throws Exception {
        List<Member> members = service.list();
        Phone p = new Phone();
        p.setName("one"); // 사용자마다 같은 이름의 휴대폰 등록닉네임이 가능해야함
        p.setPno1("010");
        p.setPno2("0000");
        p.setPno3("1234");
        for (Member m : members) {
            service.addPhone(m, p);
        }
        List<Phone> ip = phoneRepository.findAll();
        System.out.println(ip.size());

        assertThat(ip).isNotNull().isNotEmpty().hasSize(members.size());

        assertThat(ip.get(52)).isNotEqualTo(p);

        Member m = service.get("user22"); // 한 유저가 같은 휴대폰 닉네임을 가질수 없음
        assertThat(m.getPhones()).hasSize(1);
        try{
            service.addPhone(m, p); // 에러발생해야함
        } catch(Exception e){
            return;
        }
        assertThat(1).isEqualTo(2);
    }
    @Test
    @Transactional
    public void stage4_addPhoneTest() throws Exception {
        Member m = service.get("user12");
        Phone p = new Phone();
        p.setName("one");
        p.setPno1("010");
        p.setPno2("0000");
        p.setPno3("1234");
        m = service.addPhone(m, p);
        Phone target = m.getPhones().get(0);

        target.setPno1("123"); target.setPno2("456"); target.setPno3("789");
        m = service.updatePhone(m, target);

        assertThat(m.getPhones().get(0)).isEqualTo(target).isNotEqualTo(p);
        assertThat(m.getPhones()).hasSize(1);
    }

    @Test
    public void starge5_updateAddressTest() throws Exception{
//        service.updateAddress(service.get(user.getUsername()), address).getAddress();
        Member m = service.get("user53");
        Address a = new Address();
        a.setType("0"); a.setAddress1("서울시 송파구 삼전동"); a.setAddress2("nowhere");
        m = service.updateAddress(m, a);
        String one = m.getAddress().toString();

        a = new Address();
        a.setType("1"); a.setAddress1("전라남도 광양시"); a.setAddress2("nowhere");
        m = service.updateAddress(m, a);

        assertThat(one).isNotSameAs(m.getAddress().toString());
    }

}
