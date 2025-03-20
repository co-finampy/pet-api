package com.pethost.pethost.services.stripe;

import com.stripe.Stripe;
import com.stripe.model.Account;
import com.stripe.param.AccountCreateParams;

public class CreateConnectedAccount {
    public static void main(String[] args) throws Exception {

        AccountCreateParams params = AccountCreateParams.builder()
                .setType(AccountCreateParams.Type.EXPRESS)
                .setCountry("BR")
                .setEmail("email2@teste.com")
                .build();

        Account account = Account.create(params);
        System.out.println(account.getId());
    }
}
