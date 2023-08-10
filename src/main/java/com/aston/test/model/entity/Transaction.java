package com.aston.test.model.entity;

import com.aston.test.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_account_id", referencedColumnName = "id")
    private Account firstAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_account_id", referencedColumnName = "id")
    private Account secondAccount;

    @Column
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

}
