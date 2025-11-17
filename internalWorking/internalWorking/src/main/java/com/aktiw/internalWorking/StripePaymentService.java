package com.aktiw.internalWorking;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component                   //it is used to make a Bean here
@ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
public class StripePaymentService extends PaymentServices{
    @Override
    public String pay(){
        String payment = "Stripe";
        System.out.println("Paid By: " +payment);
        return payment;
    }
}
