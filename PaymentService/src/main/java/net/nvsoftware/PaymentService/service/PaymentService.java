package net.nvsoftware.PaymentService.service;

import net.nvsoftware.PaymentService.model.PaymentRequest;
import org.springframework.stereotype.Service;


public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);
}
