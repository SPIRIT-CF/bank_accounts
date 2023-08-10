package com.aston.test.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(name = "beneficiary_id_and_pin_unique_constr", columnNames = { "beneficiary_id", "pin" }) })
@Entity
public class Account {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String pin;

    @Column
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "id")
    private Beneficiary beneficiary;

    public Account(Beneficiary beneficiary, String pin, BigDecimal balance) {
        this.beneficiary = beneficiary;
        this.pin = pin;
        this.balance = balance;
    }

}
