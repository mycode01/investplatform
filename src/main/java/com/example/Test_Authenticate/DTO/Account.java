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
@EqualsAndHashCode(of = "id")
@ToString

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

//    @Column(nullable = false)
//    private String regno; // 실제로는 평문저장이 아닌 양방향 암호화 되어 저장되었음

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Balance balance;

    @Column(nullable = false, columnDefinition = "varchar(2)")
    private String approved = "N"; // 사용가능 여부에 대한 플래그

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

}
