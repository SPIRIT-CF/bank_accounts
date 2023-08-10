package com.aston.test.listeners;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DataCreator {

    private final EntityManager entityManager;

    @EventListener(classes = ApplicationReadyEvent.class)
    @Transactional
    public void createData() {
        entityManager.createNativeQuery("""
                insert into BENEFICIARY(id, name) values (1, 'Andrew');
                insert into ACCOUNT(id, pin, balance, beneficiary_id) values (1, 1111, 2000, 1);
                insert into ACCOUNT(id, pin, balance, beneficiary_id) values (2, 2222, 6000, 1);
                                
                insert into BENEFICIARY(id, name) values (2, 'Alex');
                insert into ACCOUNT(id, pin, balance, beneficiary_id) values (3, 3333, 2000, 2);
                insert into ACCOUNT(id, pin, balance, beneficiary_id) values (4, 2222, 6000, 2);
                                
                insert into BENEFICIARY(id, name) values (3, 'Olga');
                insert into ACCOUNT(id, pin, balance, beneficiary_id) values (5, 3333, 7000, 3);
                insert into ACCOUNT(id, pin, balance, beneficiary_id) values (6, 2222, 9000, 3);
                """).executeUpdate();
    }
}
