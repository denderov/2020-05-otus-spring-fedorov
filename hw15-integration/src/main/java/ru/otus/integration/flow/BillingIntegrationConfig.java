package ru.otus.integration.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.integration.payload.ChangeBalance;

import java.math.BigDecimal;

@Configuration
public class BillingIntegrationConfig {

    @Bean
    public QueueChannel billingChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel bankChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow billingFlow() {
        return IntegrationFlows.from("billingChannel")
                .<ChangeBalance, Boolean>route(changeBalance -> {
                    if (changeBalance.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                        return false;
                    } else {
                        return true;
                    }
                }, mapping -> mapping
                        .subFlowMapping(false, sf -> sf.handle("billingServiceImpl","withdrawal"))
                        .subFlowMapping(true, sf -> sf.handle("billingServiceImpl","refill")))
                .channel("bankChannel")
                .get();
    }
}
