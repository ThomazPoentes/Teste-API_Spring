package org.example.requests;

import com.mercadopago.resources.customer.Customer;
import com.softexpert.projetotesteapi.service.MercadoPagoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MercadoPagoServiceTest {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Test
    void testSendBoletoSuccess() {
        Customer customer = new Customer();
        String link = mercadoPagoService.sendBoleto(customer);
        assertTrue(link.contains("http://"));
    }

}