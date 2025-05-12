package ru.payShare.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Debt {
    private Long id; //id задолженности
    private Long creditorId; //Тот, кому должны деньги или кто предоставил долг.
    private Long debtorId; //Тот, кто должен деньги или обязан вернуть долг.
    private BigDecimal value; //Размер задолженности
}
