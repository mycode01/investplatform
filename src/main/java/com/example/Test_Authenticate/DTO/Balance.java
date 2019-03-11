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
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of="bid")
@ToString
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    @OneToMany(mappedBy = "balance")
    @JsonIgnore
    private List<BalanceHistory> balanceHistories;

    @Column(nullable = false, columnDefinition = "number(11)")
    private Long availables = 0L;

    @Column(nullable = false, columnDefinition = "number(11)")
    private Long owned = 0L;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;
}
