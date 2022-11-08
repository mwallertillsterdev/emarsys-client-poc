package com.tillster.poc.emarsys;

import com.emn8.mobilem8.admin.IMobileM8AdminService;
import com.emn8.mobilem8.admin.OrderList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmarsysClientRunner implements CommandLineRunner {

    @Inject
    EmarsysApiClient emarsysApiClient;

    @Inject
    IMobileM8AdminService mobileM8AdminService;

    @Override
    public void run(String... args) throws Exception {

        // Start
        // End
        // UseEmarsysIdorEmail

        OrderList orderList = mobileM8AdminService.findOrders("ADMIN_c6c08acc-2d49-41c8-a5e4-400a45ed5e36",
                null, 0, 50, Arrays.asList());
        //

        List<SampleObject> sampleObjects = orderList.getOrders().stream().flatMap(o ->
                Arrays.stream(o.getLineItems()).map(li ->
                        SampleObject.builder()
                                .itemId(li.getId())
                                .price(li.getPrice().toString())
                                .orderId(o.getGuid())
                                .timestamp(o.getOrderTime())
                                .customerIdentifier(o.getUserName())
                                .quantity(new Integer(li.getQuantity()).toString())
                                .build()
                )
        ).collect(Collectors.toList());

        emarsysApiClient.createOrder(sampleObjects);
    }
}
