package com.example.Test_Authenticate.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of="fundItemId")
@ToString(exclude = {"investors"})
public class FundItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fundItemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long fundAmount;

    @Column(nullable = false)
    private String fundType; // 펀딩모집타입.. 기업대외비이므로 대충 사용함

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="fundItemId")
    private FundInfo fundInfo; // 기타펀딩정보.. 테스트용이므로 대충

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="fundItemId")
    @JsonIgnore
    private List<FundInvest> investors;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="fundItemId")
    private List<Construction> constructions;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

}

