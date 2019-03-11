package com.example.Test_Authenticate.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of="uid")
@ToString(exclude = {"upw", "address", "account","invests" })

public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String uid;

    @JsonIgnore
    @Column(nullable = false, length = 200)
    private String upw;

    @Column(nullable = false)
    private String uname; //편의상

    @Column(nullable = false, unique = true, length = 50)
    private String uemail;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private List<Phone> phones;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Address address;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="id")
    private List<FundInvest> invests;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="id")
    private List<MemberRole> roles;

    @Column(nullable = false)
    private String isActive; // 고객타입, 미성년, 국가코드 등 더 있지만 편의상 여기까지

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;


    public void addRole(MemberRole role){
        if(roles == null){
            roles = new ArrayList<>();
        }
        roles.add(role);
    }

    public void addPhone(Phone phone){
        if(phones == null){
            phones = new ArrayList<>();
        }
        phones.add(phone);
    }

    public void addInvests(FundInvest invest){
        if(invests == null){
            invests = new ArrayList<>();
        }
        invests.add(invest);
    }

}
