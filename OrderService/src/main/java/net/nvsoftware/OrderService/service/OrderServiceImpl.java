package net.nvsoftware.OrderService.service;

import lombok.extern.log4j.Log4j2;
import net.nvsoftware.OrderService.model.OrderRequest;
import net.nvsoftware.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Start: OrderService placeOrder");
        //use OrderService to create a OrderEntity with Status Created, ORM JPA save to database
        //call ProductService to check product quantity, if ok reduce it, else throw not enough
        //call PaymentService to charge, if success, make order paid,else cancelled
        log.info("End: OrderService placeOrder");
        return 88;
    }
}
