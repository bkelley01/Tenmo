package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

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
            ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL + "transfers/user/" + currentUser.getUser().getId(),
                    HttpMethod.GET, makeAuthEntity(), Transfer[].class);

            allTransfers = response.getBody();

            System.out.println("-------------------------------------------\n" +
                    "Transfers\n" +
                    "ID          From/To                 Amount\n" +
                    "-------------------------------------------\n");
            if (allTransfers != null) {


                for (Transfer transfer : allTransfers) {

                    System.out.println(transfer.getTransfer_id() + "        " + transfer.getAccount_from() + "/" + transfer.getAccount_to() + "               $" + transfer.getAmount());
                    System.out.println();
                }
            }

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return allTransfers;
    }

    public Transfer getTransferById(Long id) {
        Transfer transfer = null;
        try {
            ResponseEntity<Transfer> response = restTemplate.exchange((API_BASE_URL + "transfers/" + id),
                    HttpMethod.GET, makeAuthEntity(), Transfer.class);
            transfer = response.getBody();
            System.out.println("--------------------------------------------\n" +
                    "Transfer Details\n" +
                    "--------------------------------------------\n" +
                    "Id: " + transfer.getTransfer_id() + "\n" +
                    "From: " + transfer.getAccount_from() + "\n" +
                    "To: " + transfer.getAccount_to() + "\n" +
                    "Type: " + transfer.getTransfer_type_id() + "\n" +
                    "Status: " + transfer.getTransfer_status_id() + "\n" +
                    "Amount: $" + transfer.getAmount());
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return transfer;
    }

//    public TransferDTO addTransfer(Transfer newTransfer) {
//        Transfer createdTransfer = null;
//        try {
//            ResponseEntity<Transfer> response = this.restTemplate.exchange(
//                    API_BASE_URL + "/transfers/",
//                    HttpMethod.POST, this.makeTransferEntity(newTransfer),
//                    Transfer.class);
//            createdTransfer = response.getBody();
//
//        } catch (ResourceAccessException | RestClientResponseException e) {
//            BasicLogger.log(e.getMessage());
//        }
//
//        return createdTransfer;
//    }


    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(transfer, headers);
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
