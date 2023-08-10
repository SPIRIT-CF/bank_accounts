package com.aston.test.model.repository;

import com.aston.test.model.entity.Account;
import com.aston.test.model.entity.Beneficiary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "beneficiary_entity-graph")
    boolean existsByBeneficiaryAndPin(Beneficiary beneficiary, String pin);

}
