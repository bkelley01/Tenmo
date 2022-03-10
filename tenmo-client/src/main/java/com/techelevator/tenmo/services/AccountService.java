package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {

    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    private static final String API_BASE_URL = "https://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();


    public BigDecimal getBalance(String username) {
        BigDecimal balance = null;
        try {
            ResponseEntity<BigDecimal> response = restTemplate.exchange(API_BASE_URL + "balance/" + username,
                    HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
            balance = response.getBody();
            System.out.println(balance);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

//    private HttpEntity<Account> makeAccountEntity(Account account) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearer(setAuthToken();
//        return new HttpEntity<>(auction, headers);
//    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}
