package com.softexpert.projetotesteapi.service;

import com.mercadopago.resources.customer.Customer;
import com.softexpert.projetotesteapi.dto.PaymentBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaymentBodyMapper {

    @Mapping(target = "endereco", source = "address.streetName")
    PaymentBody toPaymentBody(Customer customer);

}
