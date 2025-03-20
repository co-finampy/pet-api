package com.pethost.pethost.services.stripe;

import com.stripe.model.Transfer;
import com.stripe.param.TransferCreateParams;

public class TransferFunds {
    public static void main(String[] args) throws Exception {
        TransferCreateParams params =
         TransferCreateParams.builder()
                 .setAmount(800L)
                 .setCurrency("BRL")
                 .setDescription("acc_host_id")
                 .build();
        Transfer transfer = Transfer.create(params);
        System.out.println(transfer.getId());
    }
}
