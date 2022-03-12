package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class UserService {
    AuthenticatedUser currentUser = new AuthenticatedUser();
    private String API_BASE_URL ;
    private final RestTemplate restTemplate = new RestTemplate();

    public UserService(String API_BASE_URL,AuthenticatedUser authenticatedUser) {
        this.API_BASE_URL=API_BASE_URL;
        this.currentUser = authenticatedUser;
    }

    public User[] getAllUsers() {
        User[] allUsers = null;
        try {
            ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "/users/",
                    HttpMethod.GET, makeAuthEntity(), User[].class);
            allUsers = response.getBody();
            System.out.println("-------------------------------------------\n" +
                    "Users\n" +
                    "ID          Name\n" +
                    "-------------------------------------------");
            if (allUsers != null) {
                for (User user : allUsers) {
                    System.out.println(user.getId() + "        " + user.getUsername());
                }
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return allUsers;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}