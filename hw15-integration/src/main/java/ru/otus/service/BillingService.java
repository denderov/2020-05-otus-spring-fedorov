package ru.otus.service;

import ru.otus.domain.Account;
import ru.otus.integration.payload.ChangeBalance;

import java.math.BigDecimal;

public interface BillingService {
    String newAccount(String accountNumber);
    String refill(ChangeBalance changeBalance);
    String withdrawal(ChangeBalance changeBalance);
}
