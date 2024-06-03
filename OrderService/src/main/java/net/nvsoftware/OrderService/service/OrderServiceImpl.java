package net.nvsoftware.OrderService.service;

import lombok.extern.log4j.Log4j2;
import net.nvsoftware.OrderService.client.PaymentServiceFeignClient;
import net.nvsoftware.OrderService.client.ProductServiceFeignClient;
import net.nvsoftware.OrderService.entity.OrderEntity;
import net.nvsoftware.OrderService.model.OrderRequest;
import net.nvsoftware.OrderService.model.OrderResponse;
import net.nvsoftware.OrderService.model.PaymentRequest;
import net.nvsoftware.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;
    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public long placeOrder(OrderRequest orderRequest) {//TODO:Transaction
        log.info("Start: OrderService placeOrder");

        // Check if quantity is 0 or less, as 'long' cannot be null
        if (orderRequest.getQuantity() <= 0) {
            log.error("Invalid quantity: " + orderRequest.getQuantity());
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        //use OrderService to create a OrderEntity with Status Created, ORM JPA save to database
        OrderEntity orderEntity = OrderEntity.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .totalAmount(orderRequest.getTotalAmount())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .build();
        orderRepository.save(orderEntity);
        log.info("Process: OrderService placeOrder save OrderEntity with orderId: " + orderEntity.getId());
        //call ProductService to check product quantity, if ok reduce it, else throw not enough
        productServiceFeignClient.reduceQuantity(orderEntity.getProductId(), orderEntity.getQuantity());
        log.info("Process: OrderService placeOrder FeignCall ProductService reduceQuantity");


        //call PaymentService to charge, if success, make order paid,else cancelled
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .totalAmount(orderRequest.getTotalAmount())
                .build();
        String orderStatus = null;

        try {
            paymentServiceFeignClient.doPayment(paymentRequest);
            orderStatus = "PAID";
            log.info("Process: Service placeOrder FeignCall PaymentService doPayment PAID");
        }catch (Exception e) {
            orderStatus = "PAYMENT_FAILED";
            log.info("Process: Service placeOrder FeignCall PaymentService doPayment FAILED");
        }

        orderEntity.setOrderStatus(orderStatus);
        orderRepository.save(orderEntity);

        log.info("End: OrderService placeOrder Done with orderId: " + orderEntity.getId());
        return orderEntity.getId();
    }

    @Override
    public OrderResponse getOrderDetailById(long orderId) {
        log.info("Start: OrderService getOrderDetailById");
        OrderEntity orderEntity = orderRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException(" OrderService getOrderById: Order Not Found With Id"));

        OrderResponse.ProductResponse productResponse = restTemplate.getForObject(
                "http://localhost:8001/product/" + orderEntity.getProductId(),
                OrderResponse.ProductResponse.class
        );

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orderEntity.getId())
                .orderDate(orderEntity.getOrderDate())
                .orderStatus(orderEntity.getOrderStatus())
                .totalAmount(orderEntity.getTotalAmount())
                .productResponse(productResponse)
                .build();
        log.info("End: OrderService getOrderDetailById");
        return orderResponse;
    }
}
