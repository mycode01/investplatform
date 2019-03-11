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
@EqualsAndHashCode(of="pid")
@ToString

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pno1;

    @Column(nullable = false)
    private String pno2;

    @Column(nullable = false)
    private String pno3;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

}
