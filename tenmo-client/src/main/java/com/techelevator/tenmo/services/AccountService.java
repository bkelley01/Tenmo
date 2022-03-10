package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.http.HttpHeaders;

public class AccountService {
    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    private static final String API_BASE_URL = "https://localhost:8080";
    private final RestTemplate restTemplate = new RestTemplate();

//    public BigDecimal getBalance(String username) {
//        HttpHeaders headers = new org.springframework.http.HttpHeaders()
//    }

    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    private HttpEntity<Account> makeAccountEntity(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearer(setAuthToken();
        return new HttpEntity<>(auction, headers);
    }
}
