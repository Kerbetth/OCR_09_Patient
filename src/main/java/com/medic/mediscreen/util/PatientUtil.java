package com.medic.mediscreen.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * this service inject defaut data define in the patient_data_init.sql file at the beginning of the application
 */

@Service
@Slf4j
public class PatientUtil {

    @PersistenceContext
    public EntityManager em;
    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;
    @Value("${executiveTest}")
    private boolean executiveTest;

    @PostConstruct
    public void injectData() {
        if (executiveTest) {
            TransactionTemplate tmpl = new TransactionTemplate(txManager);
            tmpl.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    String queryContent = null;
                    BufferedReader bufferedReader = null;
                    em.createNativeQuery("DELETE FROM patient").executeUpdate();
                    bufferedReader =  new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/patient_data_init.sql")));
                    em.createNativeQuery(bufferedReader.lines().collect(Collectors.joining())).executeUpdate();

                    log.info("initial data inserted");
                }
            });
        }
    }

}