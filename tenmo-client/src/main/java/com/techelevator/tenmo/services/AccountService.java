package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.apiguardian.api.API;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {

    AuthenticatedUser currentUser = new AuthenticatedUser();
    private String API_BASE_URL ;
    private final RestTemplate restTemplate = new RestTemplate();

    public AccountService(String API_BASE_URL,AuthenticatedUser authenticatedUser) {
        this.API_BASE_URL=API_BASE_URL;
        this.currentUser = authenticatedUser;
    }

    public BigDecimal getBalance() {
        BigDecimal balance = null;
        try {
            ResponseEntity<BigDecimal> response = restTemplate.exchange(API_BASE_URL + "balance/" + currentUser.getUser().getUsername(),
                    HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
            balance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public Account getAccountByUserId(AuthenticatedUser authenticatedUser, Long userId) {

        Account account = null;
        try {
            account = restTemplate.exchange(API_BASE_URL + "account/user/" + userId,
                    HttpMethod.GET,
                    makeAuthEntity(),
                    Account.class).getBody();
        } catch(RestClientResponseException e) {
            System.out.println("Could not complete request. Code: " + e.getRawStatusCode());
        } catch(ResourceAccessException e) {
            System.out.println("Could not complete request due to server network issue. Please try again.");
        }

        return account;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }

}
