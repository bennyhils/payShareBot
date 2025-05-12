package ru.payShare.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class Payment {
    private Long id; //id оплаты
    private Long payerId; //Телеграм id пользователя совершившего оплату
    private Set<Long> consumerIds; //Телеграм id пользователей-потребителей результата оплаты
    private BigDecimal value; //Значение размера оплаты
    private Instant timestamp; //Временная метка фиксации оплаты
}
