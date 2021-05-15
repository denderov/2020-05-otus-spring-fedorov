package ru.otus.integration.payload;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ChangeBalance {
    String accountNum;
    BigDecimal amount;
}
