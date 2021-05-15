package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Account;
import ru.otus.integration.payload.ChangeBalance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BillingServiceImpl implements BillingService {
    Map<String, Account> accounts = new HashMap<>();

    @Override
    public String newAccount(String accountNumber) {
        accounts.put(accountNumber, new Account(accountNumber, BigDecimal.ZERO));
        return "OK";
    }

    @Override
    public String refill(ChangeBalance changeBalance) {
        String accountNumber = changeBalance.getAccountNum();
        BigDecimal amount = changeBalance.getAmount();
        Account account = accounts.get(accountNumber);
        if (account == null) {
            return String.format("Счет %s не найден.", accountNumber);
        }
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        return String.format("Баланс пополнен на %s. Новый баланс %s", amount, newBalance);
    }

    @Override
    public String withdrawal(ChangeBalance changeBalance) {
        String accountNumber = changeBalance.getAccountNum();
        BigDecimal amount = changeBalance.getAmount();
        Account account = accounts.get(accountNumber);
        if (account == null) {
            return String.format("Счет %s не найден.", accountNumber);
        }
        BigDecimal newBalance = account.getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            return String.format("Попытка установить баланс на уровне %s. Баланс не может быть меньше 0.", newBalance);
        }
        account.setBalance(newBalance);
        return String.format("Баланс пополнен на %s. Новый баланс %s", amount, newBalance);
    }
}
