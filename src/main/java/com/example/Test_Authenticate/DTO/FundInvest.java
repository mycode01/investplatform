package com.example.Test_Authenticate.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of="id")
@ToString
public class FundInvest { // 투자내역에 대한 내역.. 대외비므로 대충 구현
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ivid;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="fundItemId")
    @JsonIgnore
    private FundItem fundItem;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private Long investamount;

    @Column(nullable = false, columnDefinition = "varchar(2)")
    private String isValid;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;
}
