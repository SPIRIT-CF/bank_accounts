package com.aston.test.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "beneficiary")
@NamedEntityGraph(name = "beneficiary_entity-graph", attributeNodes = @NamedAttributeNode("accounts"))
public class Beneficiary {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiary")
    private List<Account> accounts;


}
