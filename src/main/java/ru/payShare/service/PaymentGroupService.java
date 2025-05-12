package ru.payShare.service;

import org.springframework.stereotype.Service;
import ru.payShare.model.PaymentGroup;

@Service
public class PaymentGroupService {

    public void save(PaymentGroup group) {
        if (group.getId() == null) {
            //insert
        } else {
            //update
        }
    }
}
