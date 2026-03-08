package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Register adapters
        Map<String, PaymentGateway> gateways = new HashMap<>();
        gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
        gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));

        // We can either pass the map to a single service, or pass specific gateways to different services.
        // Let's create two services to fully demonstrate decoupling.
        OrderService svcFastPay = new OrderService(gateways.get("fastpay"));
        OrderService svcSafeCash = new OrderService(gateways.get("safecash"));

        String id1 = svcFastPay.charge("cust-1", 1299);
        String id2 = svcSafeCash.charge("cust-2", 1299);
        
        System.out.println(id1);
        System.out.println(id2);
    }
}
