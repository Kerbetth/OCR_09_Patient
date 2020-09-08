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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * createAccount() method create a new user account with encrypted password and save it in database
 * getAccountInfo() method retrieve name and email from the user
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
                    em.createNativeQuery("DELETE FROM patient").executeUpdate();
                    try {
                        queryContent = new String(Files.readAllBytes(Paths.get("src/main/resources/patient_data_init.sql")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    em.createNativeQuery(queryContent).executeUpdate();

                    log.info("initial data inserted");
                }
            });
        }
    }

}