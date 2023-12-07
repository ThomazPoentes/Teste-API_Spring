package com.softexpert.projetotesteapi.service;

import com.mercadopago.resources.customer.Customer;
import com.softexpert.projetotesteapi.dto.PaymentBody;
import com.softexpert.projetotesteapi.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.softexpert.projetotesteapi.exception.BoletoSendingException;


import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.base_url}")
    private String mercadoPagoBaseUrl;

    @Value("${mercadopago.access_token}")
    private String mercadoPagoAccessToken;

    public String sendBoleto(Customer customer) {
        var restTemplate = new RestTemplate();
        var url = mercadoPagoBaseUrl + "/checkout/preferences";

        var phone = customer.getPhone();
        var phoneString = isNull(phone) ? null : phone.getAreaCode() + phone.getNumber();
        var paymentBody =
                PaymentBody.builder()
                        .email(customer.getEmail())
                        .telefone(phoneString)
                        .nome(customer.getFirstName())
                        .sobrenome(customer.getLastName())
                        .endereco(String.valueOf(customer.getAddress()))
                        .quantidade(null)
                        .build();

        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + mercadoPagoAccessToken);

        var entity = new HttpEntity<>(paymentBody, headers);

        var response = restTemplate.postForEntity(url, entity, PaymentResponse.class);

        if (nonNull(response.getBody()) && response.getStatusCode().is2xxSuccessful())
            return response.getBody().getLink();
        else if(nonNull(response.getBody()))
            throw new BoletoSendingException(response.getBody().toString(), response.getStatusCodeValue());
        else
            throw new BoletoSendingException("Erro ao enviar o boleto", response.getStatusCodeValue());
    }

}