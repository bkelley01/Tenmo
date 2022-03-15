package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.util.BasicLogger;

import java.math.BigDecimal;
import java.util.Scanner;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    private void viewCurrentBalance() {

        AccountService accountService = new AccountService(API_BASE_URL, currentUser);

        try {
            System.out.println("The current balance is: $" + accountService.getBalance());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    private void viewTransferHistory() {
        // TODO Auto-generated method stub
        TransferService transferService = new TransferService(API_BASE_URL, currentUser);

        try {
            transferService.getAllTransfersById();
            Long id = (long) consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
            transferService.getTransferById(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }

    }

    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    private void sendBucks() {
        TransferService transferService = new TransferService(API_BASE_URL, currentUser);
        UserService userService = new UserService(API_BASE_URL, currentUser);
//        AccountService accountService = new AccountService(API_BASE_URL, currentUser);

        TransferDTO transfer = new TransferDTO();

        userService.getAllUsers();
        int userID = consoleService.promptForInt("Enter ID of user you are sending to (0 to cancel): ");
        BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");
        transfer.setAmount(amount);
        transfer.setRecipientUserId((long) userID);

        try {
//            if (amount.compareTo(accountService.getBalance()) <= 0) {
//                transferService.addTransfer(transfer);
//            } else System.out.println("You cannot transfer more TEBucks than you have! Try again.");
            if (currentUser.getUser().getId() != userID) {
                transferService.addTransfer(transfer);
                System.out.println("Your transfer is complete.");
            } else System.out.println("You cannot transfer TEBucks to yourself! Try again.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid Selection, please try again.");
            BasicLogger.log(e.getMessage());
        }

    }

    private void requestBucks() {
        // TODO Auto-generated method stub

    }

}
