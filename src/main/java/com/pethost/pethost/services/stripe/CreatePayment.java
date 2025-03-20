package com.pethost.pethost.services.stripe;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class CreatePayment {
    public static void main(String[] args) throws Exception {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(1000L)
                        .setCurrency("brl")
                        .setPaymentMethod("card")
                        .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println(paymentIntent.getId());
    }
}
