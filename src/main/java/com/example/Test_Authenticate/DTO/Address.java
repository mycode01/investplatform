package com.example.Test_Authenticate.DTO;

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
@ToString(exclude = {"id", "regdate","updatedate"})

public class Address {
    // 다음카카오 주소찾기이용
    // 지번, 도로명주소를 겸용할수있도록 플래그이용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(2) default '0'")
    private String type;

    @Column(nullable = false)
    private String address1;

    @Column(nullable = false)
    private String address2; // 수동으로 입력하는 상세주소

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

}
