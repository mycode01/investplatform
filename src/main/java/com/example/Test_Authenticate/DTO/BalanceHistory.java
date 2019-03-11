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
@ToString(exclude = {"balance"})
public class BalanceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="bid")
    @JsonIgnore
    private Balance balance;

    @Column(nullable = false)
    private String type; // 출금 입금등의 operation 타입

    @Column
    private String description;

    @Column(nullable = false, columnDefinition = "number(11) default 0")
    private Long amount;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;
}
