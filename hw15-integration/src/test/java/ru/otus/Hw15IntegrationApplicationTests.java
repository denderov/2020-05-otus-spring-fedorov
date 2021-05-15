package ru.otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.integration.gateway.BillingGateway;
import ru.otus.integration.payload.ChangeBalance;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@EnableIntegration
@SpringBootTest
class Hw15IntegrationApplicationTests {

    @Autowired
    private BillingGateway billingGateway;

    @Test
    void sendingMessage() {
        String accountNum = "1";
        String stringAmount = "100";
        BigDecimal amount = new BigDecimal(stringAmount);
        ChangeBalance changeBalance = new ChangeBalance(accountNum, amount);
        assertThat(billingGateway.process(changeBalance).getText()).isEqualTo("Счет 1 не найден.");
    }

}
