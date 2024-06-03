package net.nvsoftware.PaymentService.service;

import net.nvsoftware.PaymentService.model.PaymentRequest;
import net.nvsoftware.PaymentService.model.PaymentResponse;
import org.springframework.stereotype.Service;


public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailByOrderId(long orderId);
}
