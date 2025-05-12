package ru.payShare.model;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
public class PaymentGroup {
    private Long id; //id группы
    private List<Payment> paymentList; //Список всех оплат, входящих в группу
    private Set<Long> memberIds; //Набор Телеграм id участников группы
    private Instant creationTimestamp; //Временная метка создания группы
}
