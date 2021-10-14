package com.tradebrite.bank_accounts.integration;

import com.tradebrite.bank_accounts.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getAccounts_emptyDatabase_returnsEmptyList() {
        List<Account> accounts = List.of(restTemplate.getForObject(baseUrl, Account[].class));
        assertEquals(0, accounts.size());
    }

    @Test
    public void getAccounts_addOneElement_returnsSameSizeList() {
        Account testAccount = new Account(null, "1234-1234", 5000l);
        restTemplate.postForObject(baseUrl, testAccount, Account.class);
        List<Account> accounts = List.of(restTemplate.getForObject(baseUrl, Account[].class));
        assertEquals(1, accounts.size());
    }

    @Test
    public void getAccountById_returnsAccountBySameId() {
        Account testAccount = new Account(null, "1234-1234", 5000l);
        restTemplate.postForObject(baseUrl, testAccount, Account.class);
        Account testAccount2 = new Account(null, "1234-5678", 100l);
        restTemplate.postForObject(baseUrl, testAccount2, Account.class);
        Account result = restTemplate.getForObject(baseUrl + "/2", Account.class);
        assertEquals(testAccount2.getNumber(), result.getNumber());
    }

    @Test
    public void updateAccount_returnsUpdatedAccount() {
        Account testAccount = new Account(null, "1234-1234", 5000l);
        restTemplate.postForObject(baseUrl, testAccount, Account.class);

        testAccount.setNumber("9876-9876");
        restTemplate.put(baseUrl + "/1", testAccount);
        Account result = restTemplate.getForObject(baseUrl + "/1", Account.class);

        assertEquals("9876-9876", result.getNumber());
    }

    @Test
    public void deleteCityById_withSomePostedAccounts_getAllShouldReturnRemaining() {
        Account testAccount = new Account(null, "1234-1234", 5000l);
        restTemplate.postForObject(baseUrl, testAccount, Account.class);
        Account testAccount2 = new Account(null, "1234-5678", 100l);
        restTemplate.postForObject(baseUrl, testAccount2, Account.class);
        Account testAccount3 = new Account(null, "9876-9876", 53637l);
        restTemplate.postForObject(baseUrl, testAccount3, Account.class);
        List<Account> testAccounts = new ArrayList<>();
        testAccounts.add(testAccount);
        testAccounts.add(testAccount2);
        testAccounts.add(testAccount3);

        testAccount.setId(restTemplate.getForObject(baseUrl + "/1", Account.class).getId());
        testAccount.setId(restTemplate.getForObject(baseUrl + "/2", Account.class).getId());
        testAccount.setId(restTemplate.getForObject(baseUrl + "/3", Account.class).getId());

        restTemplate.delete(baseUrl + "/2");
        testAccounts.remove(testAccount2);

        List<Account> accounts = List.of(restTemplate.getForObject(baseUrl, Account[].class));
        assertEquals(testAccounts.size(), accounts.size());
    }
}
