package com.aktiw.internalWorking;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider" , havingValue = "razorpay")
public class RazorpayPaymentService extends PaymentServices {
    @Override
    public String pay(){
        String payment = "Razorpay";
        System.out.println("Paid By: " +payment);
        return payment;
    }
}
