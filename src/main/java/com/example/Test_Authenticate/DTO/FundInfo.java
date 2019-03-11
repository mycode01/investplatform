package com.example.Test_Authenticate.DTO;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of="fundItemId")
@ToString
public class FundInfo {
    @Id
    @GeneratedValue
    private Long fundItemId;

    @Column
    private String description;

    @Column
    private String grade;
    // 더 있으나 대충씁시다

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;
}