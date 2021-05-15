package ru.otus.integration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.integration.payload.ChangeBalance;
import ru.otus.integration.payload.Status;

@MessagingGateway
public interface BillingGateway {

    @Gateway(requestChannel = "billingChannel", replyChannel = "bankChannel")
    Status process(ChangeBalance changeBalance);

}
