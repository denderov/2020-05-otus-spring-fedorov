package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.common.IOService;
import ru.otus.integration.gateway.BillingGateway;
import ru.otus.integration.payload.ChangeBalance;
import ru.otus.service.BillingService;

import java.math.BigDecimal;
import java.util.Arrays;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final BillingService billingService;
    private final BillingGateway billingGateway;
    private final IOService ioService;

    @ShellMethod(value = "Add account", key = {"a"})
    public String addAccount() {
        ioService.println("Введите номер нового счета");
        String accountNum = ioService.readLine();
        return billingService.newAccount(accountNum);
    }

    @ShellMethod(value = "Change balance", key = {"b"})
    public String addAmount() {
        ioService.println("Введите номер счета");
        String accountNum = ioService.readLine();
        ioService.println("Введите сумму");
        String stringAmount = ioService.readLine();
        BigDecimal amount = new BigDecimal(stringAmount);
        ChangeBalance changeBalance = new ChangeBalance(accountNum, amount);
        return billingGateway.process(changeBalance).getText();
    }
}
