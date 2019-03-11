package com.example.Test_Authenticate.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of="cid")
@ToString
public class Construction { // fundConstruction 진행상황에 해당
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    //대외비므로 쓰지 않음


    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

}
