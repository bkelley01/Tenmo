package com.techelevator.tenmo;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.HashSet;


@SpringBootApplication
public class TenmoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenmoApplication.class, args);
    }




}
