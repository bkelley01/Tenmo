package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class TransferService {

    AuthenticatedUser currentUser = new AuthenticatedUser();
    private String API_BASE_URL;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String API_BASE_URL, AuthenticatedUser currentUser) {
        this.API_BASE_URL = API_BASE_URL;
        this.currentUser = currentUser;

    }

    public Transfer[] getAllTransfersById() {
        Transfer[] allTransfers = null;

        Account account = new Account();

        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL + "transfers/" + currentUser.getUser().getId(),
                    HttpMethod.GET, makeAuthEntity(), Transfer[].class);

            allTransfers = response.getBody();

            System.out.println("-------------------------------------------\n" +
                    "Transfers\n" +
                    "ID          From/To                 Amount\n" +
                    "-------------------------------------------\n");
            if (allTransfers != null) {


                for (Transfer transfer : allTransfers) {
                    Long accountFrom = transfer.getAccount_from();
                    User user = user.get
                    acount = transfer.ge
                            System.out.println(transfer.getTransfer_id() + "      "  + transfer + "          " +transfer.getAccount_to() + "        " + transfer.getAmount() );
                }
            }

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
return allTransfers;
    }


    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
