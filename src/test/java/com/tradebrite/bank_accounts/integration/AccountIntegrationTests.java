package com.tradebrite.bank_accounts.integration;

import com.tradebrite.bank_accounts.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountIntegrationTests {

    private static final TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private Integer port;
    private String baseUrl;

    @BeforeEach
    public void init() {
        baseUrl = "http://localhost:" + port + "/account";
    }

    @Test
    public void addNewAccount_shouldReturnAccountNumber() {
        Account testAccount = new Account(null, "1234-1234", 5000l);
        restTemplate.postForObject(baseUrl, testAccount, Account.class);
        Account result = restTemplate.getForObject(baseUrl + "/1", Account.class);
        assertEquals(result.getNumber(), testAccount.getNumber());
    }
}
